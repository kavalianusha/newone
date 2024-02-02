package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.repository.AdminRepository;
import com.example.request.AdminRequest;

@Controller
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@PostMapping("/admin/addv")
	public String addUser(@RequestBody AdminRequest request) {
		String pwd = request.getPassword();
		String encodedPwd = passwordEncoder.encode(pwd);
		request.setPassword(encodedPwd);
		adminRepository.save(request);
		return "User added successfully";
	}

}
