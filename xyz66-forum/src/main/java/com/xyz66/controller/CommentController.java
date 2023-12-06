package com.xyz66.controller;

import com.xyz66.config.SwaggerConfig;
import com.xyz66.constants.SystemConstants;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.dto.AddCommentDto;
import com.xyz66.domain.entity.Comment;
import com.xyz66.enums.AppHttpCodeEnum;
import com.xyz66.service.CommentService;
import com.xyz66.utils.BeanCopyUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xyz66 Email:2910223554@qq.com
 */
@Api(tags = {SwaggerConfig.TAG_4})
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /***
     * 查询评论列表
     * @param articleId 文章的ID
     * @param pageNum 页面显示数
     * @param pageSize 分页大小
     * @return ResponseResult 响应结果
     */
    @ApiOperation(value = "查询评论列表")            // 给接口方法添加注释
    @ApiImplicitParams({                               // 定义请求参数
            @ApiImplicitParam(name = "articleId", value = "文章的ID"),
            @ApiImplicitParam(name = "pageNum", value = "页面显示数"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小")
    })
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId, Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.ARTICLE_COMMENT, articleId, pageNum, pageSize);   // 调用commentService的commentList方法，并返回结果
    }


    /**
     * 新增评论
     *
     * @param addCommentDto 评论json
     * @return ResponseResult
     */
    @ApiOperation(value = "发表评论")
    @ApiImplicitParam(name = "addCommentDto", value = "评论json")
    @PostMapping
    public ResponseResult addComment(@RequestBody AddCommentDto addCommentDto) {
        // 检查是否登录
        if (addCommentDto.getCreateBy() == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
        }
        Comment comment = BeanCopyUtils.copyBean(addCommentDto, Comment.class);
        return commentService.addComment(comment);
    }


    /**
     * 获取友链评论列表
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return 友链评论列表结果
     */
    @ApiOperation(value = "友链评论列表", notes = "获取一页友链评论")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页号"),
            @ApiImplicitParam(name = "pageSize", value = "每页大小")
    })
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum, Integer pageSize) {
        return commentService.commentList(SystemConstants.LINK_COMMENT, null, pageNum, pageSize);
    }

}
