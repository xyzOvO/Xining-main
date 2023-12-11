package com.xyz66.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelCategoryVo {
    
    @ExcelProperty(value = "分类名")
    private String name;
    
    //描述
    @ExcelProperty(value = "描述")
    private String description;

    //状态0:正常,1禁用
    @ExcelProperty(value = "状态0:正常,1禁用")
    private String status;
}
