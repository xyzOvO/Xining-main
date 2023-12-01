package com.xyz66.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 文章表(Article)表实体类
 *
 * @author makejava
 * @since 2022-02-01 11:36:28
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sg_article")
@Accessors(chain = true)
public class Article {
    @ApiModelProperty("id")
    @TableId
    private Long id;
    //标题
    @ApiModelProperty("标题")
    private String title;
    //文章内容
    @ApiModelProperty("文章内容")
    private String content;
    //文章摘要
    @ApiModelProperty("文章摘要")
    private String summary;

    // 所属分类id    在articleList不可以为空注意
    @ApiModelProperty("所属分类id")
    private Long categoryId;

    @ApiModelProperty("所属分类名称")
    @TableField(exist = false)
    private String categoryName;
    //缩略图
    @ApiModelProperty("缩略图")
    private String thumbnail;
    //是否置顶（0否，1是）
    @ApiModelProperty("是否置顶（0否，1是）")
    private String isTop;
    //状态（0已发布，1草稿）
    @ApiModelProperty("状态（0已发布，1草稿）")
    private String status;
    //访问量
    @ApiModelProperty("访问量")
    private Long viewCount;
    //是否允许评论 1是，0否
    @ApiModelProperty("是否允许评论 1是，0否")
    private String isComment;

    @TableField(fill = FieldFill.INSERT)
    private Long createBy;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    //删除标志（0代表未删除，1代表已删除）
    private Integer delFlag;

    public Article(Long id, long viewCount) {
        this.id = id;
        this.viewCount = viewCount;
    }
}

