package com.xyz66.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xyz66.constants.SystemConstants;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.Article;
import com.xyz66.domain.entity.Category;
import com.xyz66.domain.vo.CategoryVo;
import com.xyz66.domain.vo.PageVo;
import com.xyz66.mapper.CategoryMapper;
import com.xyz66.service.ArticleService;
import com.xyz66.service.CategoryService;
import com.xyz66.utils.BeanCopyUtils;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2022-02-02 12:29:52
 */
@Service("categoryService")
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))// 必须参数，final，@NonNull
//@AllArgsConstructor// 所有参数
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private  ArticleService articleService;

    /**
     * 获取分类列表
     * @return
     */
    @Override
    public ResponseResult getCategoryList() {
        //查询文章表  状态为已发布的文章
        List<Article> articleList = articleService.lambdaQuery()
                .eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL)
                .list();
//        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
//        articleWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
//        List<Article> articleList = articleService.list(articleWrapper);
        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
                .map(article -> article.getCategoryId())
                .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
                // 过滤掉状态为禁止的分类
                filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
                .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);
        return ResponseResult.okResult(categoryVos);
    }

    @Override
    public List<CategoryVo> listAllCategory() {
        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Category::getStatus, SystemConstants.NORMAL);
        List<Category> list = list(wrapper);
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(list, CategoryVo.class);
        return categoryVos;
    }

    @Override
    public PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper();

        queryWrapper.like(StringUtils.hasText(category.getName()),Category::getName, category.getName());
        queryWrapper.eq(Objects.nonNull(category.getStatus()),Category::getStatus, category.getStatus());

        Page<Category> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page(page,queryWrapper);

        //转换成VO
        List<Category> categories = page.getRecords();

        PageVo pageVo = new PageVo();
        pageVo.setTotal(page.getTotal());
        pageVo.setRows(categories);
        return pageVo;
    }

    @Override
    public boolean checkCategoryNameUnique(String name) {
//        if (this.count(this.lambdaQuery().eq(Category::getName, name)) > 0){
//            return false;
//        }
        if (count(new LambdaQueryWrapper<Category>().eq(Category::getName, name)) > 0) {
            return false;
        }
        return true;
    }
}

