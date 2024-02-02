package com.example.springproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springproject.entity.Post;

public interface PostRepository extends JpaRepository<Post,Integer> {

	
}
