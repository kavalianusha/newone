package com.example.springproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springproject.entity.User;
import com.example.springproject.repository.UserRepository;

@RestController
@RequestMapping("/secure/rest")
public class AdminController {

	// Anusha anu = new Anusha();
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	/* @PreAuthorize("hasAnyRole('EMPLOYEE')") */
	@PostMapping("/admin/add")
	public String addUserByAdmin(@RequestBody User user) {
		
		String pwd = user.getPassword();
		String encryptpwd = passwordEncoder.encode(pwd);
		user.setPassword(encryptpwd);
		userRepository.save(user);
		return "user added successfully....";
	}

	public BCryptPasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}

	public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
}
