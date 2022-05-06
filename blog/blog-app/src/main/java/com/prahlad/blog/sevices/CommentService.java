package com.prahlad.blog.sevices;

import com.prahlad.blog.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
