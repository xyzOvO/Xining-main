package com.xyz66.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加评论DTO")
public class AddCommentDto {
    @ApiModelProperty(notes = "评论id")
    private Long id;
    //评论类型（0代表文章评论，1代表友链评论）
    @ApiModelProperty(notes = "评论类型（0代表文章评论，1代表友链评论）")
    private String type;
    //文章id
    @ApiModelProperty(notes = "文章id")
    private Long articleId;
    //根评论id
    @ApiModelProperty(notes = "根评论id")
    private Long rootId;
    //评论内容
    @ApiModelProperty(notes = "评论内容")
    private String content;
    //所回复的目标评论的userid
    @ApiModelProperty(notes = "所回复的目标评论的userid")
    private Long toCommentUserId;
    //回复目标评论id
    @ApiModelProperty(notes = "回复目标评论id")
    private Long toCommentId;
    @ApiModelProperty(notes = "创建者id")
    private Long createBy;
    @ApiModelProperty(notes = "创建时间")
    private Date createTime;
    @ApiModelProperty(notes = "更新者id")
    private Long updateBy;
    @ApiModelProperty(notes = "更新时间")
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @ApiModelProperty(notes = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
}
