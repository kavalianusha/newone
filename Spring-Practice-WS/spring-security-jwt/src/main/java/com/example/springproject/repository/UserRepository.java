package com.example.springproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springproject.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
	
	public User findByUserNameAndPassword(String userName, String password);
}