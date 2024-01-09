package com.xyz66.controller;

import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.dto.AddArticleDto;
import com.xyz66.domain.dto.ArticleDto;
import com.xyz66.domain.entity.Article;
import com.xyz66.domain.vo.ArticleVo;
import com.xyz66.domain.vo.PageVo;
import com.xyz66.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = SwaggerConfig.TAG_1)
@RestController
@RequestMapping("/content/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 添加文章
     *
     * @param article - 文章信息
     * @return 返回添加结果
     */
    @ApiOperation(value = "添加文章")
    @ApiImplicitParam(name = "article", value = "文章信息")
    @PostMapping
    public ResponseResult add(@RequestBody AddArticleDto article) {
        return articleService.add(article);
    }


    /**
     * 获取文章列表
     *
     * @param article  文章对象
     * @param pageNum  页码
     * @param pageSize 每页数量
     * @return 返回包含文章列表的ResponseResult对象
     */
    @GetMapping("/list")
    @ApiOperation(value = "获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量"),
            @ApiImplicitParam(name = "article", value = "要查询的文章对象")}
    )
    public ResponseResult list(Article article, Integer pageNum, Integer pageSize) {
        PageVo pageVo = articleService.selectArticlePage(article, pageNum, pageSize);
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 根据id获取文章信息
     *
     * @param id 文章id
     * @return 返回包含文章信息的ResponseResult对象
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取文章信息")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "文章id")
    )
    public ResponseResult getInfo(@PathVariable(value = "id") Long id) {
        ArticleVo article = articleService.getInfo(id);
        return ResponseResult.okResult(article);
    }


    /**
     * 编辑文章
     *
     * @param article 要编辑的文章
     * @return 编辑结果
     */
    @PutMapping
    public ResponseResult edit(@RequestBody ArticleDto article) {
        articleService.edit(article);
        return ResponseResult.okResult();
    }

    /**
     * 根据文章ID删除文章
     *
     * @param id 文章ID
     * @return 删除结果
     */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据文章ID删除文章")
    @ApiImplicitParam(name = "id", value = "文章ID")
    public ResponseResult delete(@PathVariable Long id) {
        articleService.removeById(id);
        return ResponseResult.okResult();
    }

}
