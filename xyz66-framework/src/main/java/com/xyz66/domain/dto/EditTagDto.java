package com.xyz66.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "修改角色状态dto")
public class EditTagDto {

    @ApiModelProperty(notes = "标签id")
    private Long id;
    //备注
    @ApiModelProperty(notes = "标签备注")
    private String remark;
    //标签名
    @ApiModelProperty(notes = "标签名")
    private String name;
}
