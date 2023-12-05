package com.xyz66.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@ApiModel(description = "用户信息VO")
@Accessors(chain = true)
public class UserInfoVo {
    /**
     * 主键
     */
    @ApiModelProperty(notes = "主键")
    private Long id;

    /**
     * 昵称
     */
    @ApiModelProperty(notes = "昵称")
    private String nickName;

    /**
     * 头像
     */
    @ApiModelProperty(notes = "头像")
    private String avatar;

    @ApiModelProperty(notes = "性别")
    private String sex;

    @ApiModelProperty(notes = "邮箱")
    private String email;


}
