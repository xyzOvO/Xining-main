package com.xyz66.controller;


import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * 分类管理
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = SwaggerConfig.TAG_2)
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 获取分类列表
     *
     * @return 分类列表
     */
    @ApiOperation(value = "获取分类列表")
    @GetMapping("/getCategoryList")
    public ResponseResult getCategoryList() {
        return categoryService.getCategoryList();
    }

}
