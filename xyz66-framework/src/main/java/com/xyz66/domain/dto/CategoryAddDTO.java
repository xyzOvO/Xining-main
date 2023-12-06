package com.xyz66.domain.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 分类表(Category)-AddDTO
 *
 * @author xyz66 Email:2910223554@qq.com
 * @since 2023-12-06 11:21:59
 */
@TableName("sg_category")
public class CategoryAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 分类名
     */
    @ApiModelProperty("分类名")
    private String name;

    /**
     * 父分类id，如果没有父分类为-1
     */
    @ApiModelProperty("父分类id，如果没有父分类为-1")
    private Long pid;

    /**
     * 描述
     */
    @ApiModelProperty("描述")
    private String description;

    /**
     * 状态0:正常,1禁用
     */
    @ApiModelProperty("状态0:正常,1禁用")
    private String status;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    /**
     * 删除标志（0代表未删除，1代表已删除）
     */
    @ApiModelProperty("删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }


}


