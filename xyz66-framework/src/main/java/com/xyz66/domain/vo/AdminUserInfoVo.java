package com.xyz66.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "管理员用户信息VO")
public class AdminUserInfoVo {

    @ApiModelProperty(notes = "权限列表")
    private List<String> permissions;

    @ApiModelProperty(notes = "角色列表")
    private List<String> roles;

    @ApiModelProperty(notes = "用户信息")
    private UserInfoVo user;
}
