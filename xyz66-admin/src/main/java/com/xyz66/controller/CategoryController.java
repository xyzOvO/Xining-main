package com.xyz66.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.xyz66.config.SwaggerConfig;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.Category;
import com.xyz66.domain.vo.CategoryVo;
import com.xyz66.domain.vo.ExcelCategoryVo;
import com.xyz66.domain.vo.PageVo;
import com.xyz66.enums.AppHttpCodeEnum;
import com.xyz66.service.CategoryService;
import com.xyz66.utils.BeanCopyUtils;
import com.xyz66.utils.WebUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = SwaggerConfig.TAG_2)
//@Controller// EasyExcel:这里注意下注解是@Controller ，不是RestController
@RestController
@RequestMapping("/content/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取分类列表")
    @GetMapping("/listAllCategory")
    public ResponseResult listAllCategory() {
        List<CategoryVo> list = categoryService.listAllCategory();
        return ResponseResult.okResult(list);
    }

    @ApiOperation(value = "编辑分类")
    @PutMapping
    public ResponseResult edit(@RequestBody Category category) {
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping(value = "/{id}")
    public ResponseResult remove(@PathVariable(value = "id") Long id) {
        categoryService.removeById(id);
        return ResponseResult.okResult();
    }

    @ApiOperation(value = "根据id获取分类信息")
    @GetMapping(value = "/{id}")
    public ResponseResult getInfo(@PathVariable(value = "id") Long id) {
        Category category = categoryService.getById(id);
        return ResponseResult.okResult(category);
    }

    @ApiOperation(value = "添加分类")
    @PostMapping
    public ResponseResult add(@RequestBody Category category) {
        // 是否有相同分类名
        if (!categoryService.checkCategoryNameUnique(category.getName())) {
            return ResponseResult.errorResult(AppHttpCodeEnum.CLASS_NAME_DUPLICATION);
        }
        categoryService.save(category);
        return ResponseResult.okResult();
    }

    /**
     * 获取用户列表
     */
    @ApiOperation(value = "分页查询分类列表")
    @GetMapping("/list")
    public ResponseResult list(Category category, Integer pageNum, Integer pageSize) {
        PageVo pageVo = categoryService.selectCategoryPage(category, pageNum, pageSize);
        return ResponseResult.okResult(pageVo);
    }

    @ApiOperation(value = "导出分类")
    @PreAuthorize("@ps.hasPermission('content:category:export')")// 权限控制注解
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        //设置下载文件的请求头,告诉浏览器输出的是excel文件
        try {
            WebUtils.setDownLoadHeader("分类.xlsx", response);
            //获取需要导出的数据
            List<Category> categoryVos = categoryService.list();
            List<ExcelCategoryVo> excelCategoryVos = BeanCopyUtils.copyBeanList(categoryVos, ExcelCategoryVo.class);
//            String fileName = "C:\\Users\\古井枯塘\\Desktop\\分类数据.xlsx";
            System.out.println(JSON.toJSONString(excelCategoryVos));
            //把数据写入到Excel中,后台以流的形式写到客户端
            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).sheet("模板")
                    .doWrite(excelCategoryVos);

//            EasyExcel.write(response.getOutputStream(), ExcelCategoryVo.class).sheet("分类导出").doWrite(excelCategoryVos);
            System.out.println("导出成功");
//            // 使用Apache POI库创建工作簿和工作表，并将数据写入Excel  
//            Workbook workbook = new XSSFWorkbook(); // 新建工作簿  
//            Sheet sheet = workbook.createSheet("分类数据"); // 创建工作表  
//            Row row;
//            Cell cell;
//            int rowNum = 0;
//            for (ExcelCategoryVo excelCategoryVo : excelCategoryVos) {
//                row = sheet.createRow(rowNum++);
//                cell = row.createCell(0);
//                cell.setCellValue(excelCategoryVo.getName()); // 替换为你的字段名  
//                cell = row.createCell(1);
//                cell.setCellValue(excelCategoryVo.getDescription()); // 替换为你的字段名  
//                cell = row.createCell(2);
//                cell.setCellValue(excelCategoryVo.getStatus());
//                // ... 添加更多的字段 ...  
//            }
//            // 设置响应体并输出Excel文件  
//            OutputStream outputStream = response.getOutputStream();
//            workbook.write(outputStream);
//            workbook.close();

        } catch (Exception e) {
            e.printStackTrace();
            //如果出现异常也要响应json
//            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SYSTEM_ERROR);
//            WebUtils.renderString(response, JSON.toJSONString(result));
        }
    }

    @ApiOperation(value = "修改分类状态")
    @PutMapping("/updateStatus")
    public ResponseResult updateStatus(@RequestBody Category category) {
        categoryService.updateById(category);
        return ResponseResult.okResult();
    }
}
