package com.prahlad.blog.controllers;

import com.prahlad.blog.payload.ApiResponse;
import com.prahlad.blog.payload.UserDto;
import com.prahlad.blog.sevices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createUserDto=this.userService.createUser(userDto);
        return  new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }
    @PutMapping("/{userId}")
    public  ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid){
        UserDto updateUser=this.userService.updateUser(userDto,uid);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid){
        this.userService.deleteUser(uid);
        return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted success",true),HttpStatus.OK);
    }

    @GetMapping("/")
    public  ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(this.userService.getAllUsers());
    }



    @GetMapping("/{userId}")
    public  ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId){
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}
