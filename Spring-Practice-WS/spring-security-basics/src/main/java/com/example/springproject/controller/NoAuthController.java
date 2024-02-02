package com.example.springproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/noAuth")
public class NoAuthController {
	
	@GetMapping("/hiMsg")
	public String sayHi()
	{
		return "hello to spring boot from anusha";
	}

}
