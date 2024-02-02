package com.example.springproject.service;

import org.springframework.stereotype.Service;

import com.example.springproject.entity.User;

@Service
public interface UserService {
	
    public void saveUser(User user);
    public User getUserByNameAndPassword(String name, String password) throws Exception;
}
