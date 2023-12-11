package com.xyz66.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.Category;
import com.xyz66.domain.vo.CategoryVo;
import com.xyz66.domain.vo.PageVo;

import java.util.List;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-02-02 12:29:50
 */
public interface CategoryService extends IService<Category> {


    ResponseResult getCategoryList();

    List<CategoryVo> listAllCategory();

    PageVo selectCategoryPage(Category category, Integer pageNum, Integer pageSize);

    boolean checkCategoryNameUnique(String name);
}

