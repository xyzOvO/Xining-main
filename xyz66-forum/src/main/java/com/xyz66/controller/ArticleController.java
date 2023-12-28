package com.xyz66.controller;


import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.service.ArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * 文章管理
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = SwaggerConfig.TAG_1)
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/article")
public class ArticleController {
//    @Autowired
    private final ArticleService articleService;

    @GetMapping("/hotArticleList")
    @ApiOperation(value = "获取热门文章", notes = "获取热门文章")
    public ResponseResult hotArticleList() {
        ResponseResult result = articleService.hotArticleList();
        return result;
    }

    @GetMapping("/articleList")
    @ApiOperation(value = "分页查询文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小"),
            @ApiImplicitParam(name = "categoryId", value = "分类ID"),
    })
    public ResponseResult articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        return articleService.articleList(pageNum, pageSize, categoryId);
    }


//    @PreAuthorize("@test.cs('雪豹闭嘴')")
    @ApiOperation(value = "更新浏览次数+1")
    @ApiImplicitParam(name = "id", value = "文章ID")
    @PutMapping("/updateViewCount/{id}")
    public ResponseResult updateViewCount(@PathVariable("id") Long id) {
        return articleService.updateViewCount(id);
    }


    @ApiOperation(value = "获取文章详细信息")
    @ApiImplicitParam(name = "id", value = "文章ID")
    @GetMapping("/{id}")
    public ResponseResult getArticleDetail(@PathVariable("id") Long id) {
        return articleService.getArticleDetail(id);
    }
}
