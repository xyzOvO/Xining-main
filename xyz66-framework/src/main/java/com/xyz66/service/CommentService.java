package com.xyz66.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xyz66.domain.ResponseResult;
import com.xyz66.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-02-08 23:49:35
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}

