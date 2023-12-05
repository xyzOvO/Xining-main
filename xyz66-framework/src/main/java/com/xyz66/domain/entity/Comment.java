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

import java.util.Date;
/**
 * 评论表(Comment)表实体类
 *
 * @author makejava
 * @since 2022-02-08 23:49:34
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_comment")
@ApiModel(description = "添加评论实体类")
public class Comment  {
    @TableId
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
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "评论创建人")
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(notes = "评论创建时间")
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "评论更新人")
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(notes = "评论更新时间")
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    @ApiModelProperty(notes = "删除标志（0代表未删除，1代表已删除）")
    private Integer delFlag;
}
