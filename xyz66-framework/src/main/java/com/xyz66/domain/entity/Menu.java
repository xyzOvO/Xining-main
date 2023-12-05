package com.xyz66.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * 菜单权限表(Menu)表实体类
 *
 * @author makejava
 * @since 2022-08-09 23:47:50
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "菜单权限表")
@TableName("sys_menu")
@Accessors(chain = true)
public class Menu  {
    //菜单ID
    @TableId
    @ApiModelProperty(notes = "菜单id")
    private Long id;

    //菜单名称
    @ApiModelProperty(notes = "菜单名称")
    private String menuName;
    //父菜单ID
    @ApiModelProperty(notes = "父菜单id")
    private Long parentId;
    //显示顺序
    @ApiModelProperty(notes = "显示顺序")
    private Integer orderNum;
    //路由地址
    @ApiModelProperty(notes = "路由地址")
    private String path;
    //组件路径
    @ApiModelProperty(notes = "组件路径")
    private String component;
    //是否为外链（0是 1否）
    @ApiModelProperty(notes = "是否为外链（0是 1否）")
    private Integer isFrame;
    //菜单类型（M目录 C菜单 F按钮）
    @ApiModelProperty(notes = "菜单类型（M目录 C菜单 F按钮）")
    private String menuType;
    //菜单状态（0显示 1隐藏）
    @ApiModelProperty(notes = "菜单状态（0显示 1隐藏）")
    private String visible;
    //菜单状态（0正常 1停用）
    @ApiModelProperty(notes = "菜单状态（0正常 1停用）")
    private String status;
    //权限标识
    @ApiModelProperty(notes = "权限标识")
    private String perms;
    //菜单图标
    @ApiModelProperty(notes = "菜单图标")
    private String icon;
    //创建者
    @ApiModelProperty(notes = "创建者")
    private Long createBy;
    //创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    //更新者
    @ApiModelProperty(notes = "更新者")
    private Long updateBy;
    //更新时间
    private Date updateTime;
    //备注
    @ApiModelProperty(notes = "备注")
    private String remark;
    
    private String delFlag;

    @TableField(exist = false)
    private List<Menu> children;
}
