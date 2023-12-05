package com.xyz66.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加标签DTO")
public class AddTagDto {
    //备注
    @ApiModelProperty(notes = "备注")
    private String remark;
    //标签名
    @ApiModelProperty(notes = "标签名")
    private String name;
}
