package com.xyz66.domain.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * 文章表(Article)-AddDTO
 *
 * @author xyz66 Email:2910223554@qq.com
 * @since 2023-12-06 11:17:06
 */
@TableName("sg_article")
public class ArticleAddDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 文章唯一ID
     */
    @ApiModelProperty("文章唯一ID")
    private Long id;

    /**
     * 标题
     */
    @ApiModelProperty("标题")
    private String title;

    /**
     * 文章内容
     */
    @ApiModelProperty("文章内容")
    private String content;

    /**
     * 文章摘要
     */
    @ApiModelProperty("文章摘要")
    private String summary;

    /**
     * 所属分类id
     */
    @ApiModelProperty("所属分类id")
    private Long categoryId;

    /**
     * 缩略图
     */
    @ApiModelProperty("缩略图")
    private String thumbnail;

    /**
     * 是否置顶（0否，1是）
     */
    @ApiModelProperty("是否置顶（0否，1是）")
    private String isTop;

    /**
     * 状态（0已发布，1草稿）
     */
    @ApiModelProperty("状态（0已发布，1草稿）")
    private String status;

    /**
     * 访问量
     */
    @ApiModelProperty("访问量")
    private Long viewCount;

    /**
     * 是否允许评论 1是，0否
     */
    @ApiModelProperty("是否允许评论 1是，0否")
    private String isComment;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public String getIsComment() {
        return isComment;
    }

    public void setIsComment(String isComment) {
        this.isComment = isComment;
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


