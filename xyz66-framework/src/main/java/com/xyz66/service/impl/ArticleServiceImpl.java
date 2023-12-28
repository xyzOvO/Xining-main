package com.xyz66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz66.constants.SystemConstants;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.dto.AddArticleDto;
import com.xyz66.domain.dto.ArticleDto;
import com.xyz66.domain.entity.Article;
import com.xyz66.domain.entity.ArticleTag;
import com.xyz66.domain.entity.Category;
import com.xyz66.domain.vo.*;
import com.xyz66.enums.AppHttpCodeEnum;
import com.xyz66.mapper.ArticleMapper;
import com.xyz66.service.ArticleService;
import com.xyz66.service.ArticleTagService;
import com.xyz66.service.CategoryService;
import com.xyz66.utils.BeanCopyUtils;
import com.xyz66.utils.RedisCache;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service("articleService")
@Log4j2
@RequiredArgsConstructor(onConstructor =@__(@Autowired))// 要注入的不标记为final，会有问题，报空指针
//@AllArgsConstructor// 还是会循环依赖
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired // 这里会产生循环依赖，要单独注入
//    @Lazy// 懒加载，懒加载的意思是，只有在调用的时候才去初始化，初始化的时候，才会去调用构造方法，初始化的时候，才会去调用set方法
    private CategoryService categoryService;

    @Autowired
    private RedisCache redisCache;

//    @Autowired
    private final ArticleTagService articleTagService;
    @Override
    public ResponseResult hotArticleList() {
        //查询热门文章 封装成ResponseResult返回
        // 构建查询条件,查询热门文章
        // mybatis-plus,this的意思是当前类，lambdaQuery()是mybatis-plus提供的查询方法，通过继承了ServiceImpl类，可以直接调用,便于自身的查询
        // 也可以不加this.加入this是为了提高可读和一致性
        List<Article> articles = this.lambdaQuery().select(Article::getId, Article::getTitle, Article::getSummary, Article::getThumbnail, Article::getViewCount)
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .orderByDesc(Article::getViewCount)
                .last("limit 10")
                .list();
//        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        //必须是正式文章
//        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
//        //按照浏览量进行排序
//        queryWrapper.orderByDesc(Article::getViewCount);
//        //最多只查询10条
//        Page<Article> page = new Page(1,10);
//        page(page,queryWrapper);
//
//        List<Article> articles = page.getRecords();
        //bean拷贝
//        List<HotArticleVo> articleVos = new ArrayList<>();
//        for (Article article : articles) {
//            HotArticleVo vo = new HotArticleVo();
//            BeanUtils.copyProperties(article,vo);
//            articleVos.add(vo);
//        }
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return ResponseResult.okResult(vs);
    }
    
    /**
     * 分页查询文章列表
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @return
     */
    @Override
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 按时间降序排序,越新越前
        lambdaQueryWrapper.orderByDesc(Article::getCreateTime);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //articleId去查询articleName进行设置
//        for (Article article : articles) {
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);
        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //从redis中获取viewCount(浏览量)
        Integer viewCount = redisCache.getCacheMapValue("article:viewCount", id.toString());
        article.setViewCount(viewCount.longValue());
        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }

    @Override
    public ResponseResult updateViewCount(Long id) {
        //更新redis中对应 id的浏览量
        redisCache.incrementCacheMapValue("article:viewCount",id.toString(),1);
        return ResponseResult.okResult();
    }

    @Override
    @Transactional
    public ResponseResult add(AddArticleDto articleDto) {
        // 异常判断
        if (articleDto.getTitle() == null || articleDto.getTags().size() == 0 || articleDto.getCategoryId() == 0 || articleDto.getContent().length() == 0) {
            return ResponseResult.errorResult(AppHttpCodeEnum.THE_ARTICLE_IS_INCOMPLETE);
        }
        //添加 博客
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        save(article);
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(article.getId(), tagId))
                .collect(Collectors.toList());
        log.info("articleTags:{}", articleTags);
        //添加 博客和标签的关联
        articleTagService.saveBatch(articleTags);
        return ResponseResult.okResult();
    }

    @Override
    public PageVo selectArticlePage(Article article, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(article.getTitle()),Article::getTitle, article.getTitle());
        queryWrapper.like(StringUtils.hasText(article.getSummary()),Article::getSummary, article.getSummary());

        Page<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //转换成VO
        List<Article> articles = page.getRecords();

        //这里偷懒没写VO的转换 应该转换完在设置到最后的pageVo中

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(articles);
        return pageVo;
    }

    @Override
    public ArticleVo getInfo(Long id) {
        Article article = getById(id);
        //获取关联标签
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        List<ArticleTag> articleTags = articleTagService.list(articleTagLambdaQueryWrapper);
        List<Long> tags = articleTags.stream().map(articleTag -> articleTag.getTagId()).collect(Collectors.toList());

        ArticleVo articleVo = BeanCopyUtils.copyBean(article,ArticleVo.class);
        articleVo.setTags(tags);
        return articleVo;
    }

    @Override
    public void edit(ArticleDto articleDto) {
        Article article = BeanCopyUtils.copyBean(articleDto, Article.class);
        //更新博客信息
        updateById(article);
        //删除原有的 标签和博客的关联
        LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        articleTagLambdaQueryWrapper.eq(ArticleTag::getArticleId,article.getId());
        articleTagService.remove(articleTagLambdaQueryWrapper);
        //添加新的博客和标签的关联信息
        List<ArticleTag> articleTags = articleDto.getTags().stream()
                .map(tagId -> new ArticleTag(articleDto.getId(), tagId))
                .collect(Collectors.toList());
        articleTagService.saveBatch(articleTags);

    }
}
