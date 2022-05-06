package com.prahlad.blog.controllers;

import com.prahlad.blog.entities.Comment;
import com.prahlad.blog.payload.ApiResponse;
import com.prahlad.blog.payload.CommentDto;
import com.prahlad.blog.sevices.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){

        CommentDto createComment=this.commentService.createComment(comment,postId);
        return  new ResponseEntity<CommentDto>(createComment, HttpStatus.CREATED);

    }

    @DeleteMapping("/comments/{commentId}")
    public  ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){

       this.commentService.deleteComment(commentId);
       return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted done",true),HttpStatus.OK);
    }


}
