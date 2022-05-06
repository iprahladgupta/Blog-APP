package com.prahlad.blog.repository;

import com.prahlad.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer > {
}
