package com.prahlad.blog.sevices;

import com.prahlad.blog.entities.Post;
import com.prahlad.blog.payload.PostDto;
import com.prahlad.blog.payload.PostResponse;

import java.util.List;

public interface PostService {


    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

    PostDto updatePost(PostDto postDto,Integer postId);


    void deletePost(Integer postId);

    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);

    PostDto getPostById(Integer PostId);

    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all post by user
    List<PostDto> getPostByUser(Integer userId);


    //search post by keyword
    List<PostDto> searchPosts(String keyword);



}
