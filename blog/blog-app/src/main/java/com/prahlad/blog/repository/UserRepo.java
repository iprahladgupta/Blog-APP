package com.prahlad.blog.repository;

import com.prahlad.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {
	

}
