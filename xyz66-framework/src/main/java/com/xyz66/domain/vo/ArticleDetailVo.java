package com.xyz66.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "文章详情VO")
public class ArticleDetailVo {

    @ApiModelProperty(notes = "文章id")
    private Long id;
    //标题
    @ApiModelProperty(notes = "文章标题")
    private String title;
    //文章摘要
    @ApiModelProperty(notes = "文章摘要")
    private String summary;
    //所属分类id
    @ApiModelProperty(notes = "所属分类id")
    private Long categoryId;
    //所属分类名
    @ApiModelProperty(notes = "所属分类名")
    private String categoryName;
    //缩略图
    @ApiModelProperty(notes = "缩略图")
    private String thumbnail;
    //文章内容
    @ApiModelProperty(notes = "文章内容")
    private String content;
    //访问量
    @ApiModelProperty(notes = "访问量")
    private Long viewCount;

    @ApiModelProperty(notes = "创建时间")
    private Date createTime;

}
