package com.xyz66.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "添加文章DTO")
public class AddArticleDto implements Serializable {

    @ApiModelProperty(notes = "文章id")
    private Long id;
    //标题
    @ApiModelProperty(notes = "标题")
    private String title;
    //文章内容
    @ApiModelProperty(notes = "文章内容")
    private String content;
    //文章摘要
    @ApiModelProperty(notes = "文章摘要")
    private String summary;
    //所属分类id
    @ApiModelProperty(notes = "所属分类id")
    private Long categoryId;

    //缩略图
    @ApiModelProperty(notes = "缩略图")
    private String thumbnail;
    //是否置顶（0否，1是）
    @ApiModelProperty(notes = "是否置顶（0否，1是）")
    private String isTop;
    //状态（0已发布，1草稿）
    @ApiModelProperty(notes = "状态（0已发布，1草稿）")
    private String status;
    //访问量
    @ApiModelProperty(notes = "访问量")
    private Long viewCount;
    //是否允许评论 1是，0否
    @ApiModelProperty(notes = "是否允许评论 1是，0否")
    private String isComment;
    @ApiModelProperty(notes = "标签id")
    private List<Long> tags;

}
