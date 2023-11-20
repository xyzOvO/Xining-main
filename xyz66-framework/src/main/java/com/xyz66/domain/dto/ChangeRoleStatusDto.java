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
public class ChangeRoleStatusDto {

    @ApiModelProperty(notes = "角色id")
    private Long roleId;
    @ApiModelProperty(notes = "状态（0正常，1停用）")
    private String status;
}
