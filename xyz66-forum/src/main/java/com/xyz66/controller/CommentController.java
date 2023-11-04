package com.xyz66.controller;

import com.xyz66.config.SwaggerConfig;
import com.xyz66.constants.SystemConstants;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.dto.AddCommentDto;
import com.xyz66.domain.entity.Comment;
import com.xyz66.service.CommentService;
import com.xyz66.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Api(tags = {SwaggerConfig.TAG_4})
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/commentList")
    @ApiOperation(value = "查询评论列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "articleId", value = "文章的ID"),
            @ApiImplicitParam(name = "pageNum", value = "页面显示数"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小")
        }
    )
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation(value = "发表评论")
    @ApiImplicitParam(name = "addCommentDto",value = "评论json")
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto) {
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }


    @GetMapping("/linkCommentList")
    @ApiOperation(value = "友链评论列表", notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")
    }
    )
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }
}
