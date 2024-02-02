package com.example.springproject.config;

import java.util.Map;

import com.example.springproject.entity.User;

public interface JwtGeneratorInterface {
	
	
	Map<String, String> generateToken(User user);
	
}

