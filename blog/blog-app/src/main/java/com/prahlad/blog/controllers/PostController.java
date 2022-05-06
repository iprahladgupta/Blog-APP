package com.prahlad.blog.controllers;


import com.prahlad.blog.Config.AppConstants;
import com.prahlad.blog.entities.Post;
import com.prahlad.blog.payload.ApiResponse;
import com.prahlad.blog.payload.PostDto;
import com.prahlad.blog.payload.PostResponse;
import com.prahlad.blog.sevices.FileService;
import com.prahlad.blog.sevices.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    @Value("${project.image}")
     private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,@PathVariable Integer categoryId){

        PostDto  createPost=this.postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);

    }

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
        List<PostDto> posts=this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> posts=this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get all the posts
    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPost(@RequestParam (value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize,
                                                   @RequestParam(value ="sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
                                                   @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        PostResponse postResponse=this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
    }



    @GetMapping("/posts/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
       PostDto postDto=postService.getPostById(postId);
       return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

    //delete post

    @DeleteMapping("/posts/{postId}")
    public ApiResponse deletePost(@PathVariable Integer postId){
        this.postService.deletePost(postId);
        return  new ApiResponse("post deleted",true);

    }

    @PutMapping("/posts/{postId}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId) {

        PostDto updatedPost = this.postService.updatePost(postDto, postId);
        return  new ResponseEntity<PostDto>(updatedPost,HttpStatus.OK);
    }

    // search by title
    @GetMapping("/posts/search/{keywords}")
    public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
        List<PostDto> result=this.postService.searchPosts(keywords);
        return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
    }

    @PostMapping("/post/image/upload/{postId}")
    public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image")  MultipartFile image ,@PathVariable Integer postId) throws IOException {

        PostDto postDto=this.postService.getPostById(postId);

        String fileName=this.fileService.uploadImage(path,image);

        postDto.setImageName(fileName);
        PostDto updatePost=this.postService.updatePost(postDto,postId);
        return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);

    }

    @GetMapping(value = "/post/image/{imageName}",produces= MediaType.IMAGE_JPEG_VALUE)
    public  void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream resources=this.fileService.getResource(path,imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resources,response.getOutputStream());
    }
}
