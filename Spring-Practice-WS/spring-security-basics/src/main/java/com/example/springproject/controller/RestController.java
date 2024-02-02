package com.example.springproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/result")
public class RestController {
	
	@GetMapping("/getMsg")
	public String greetings()
	{
		return "message from anusha to spring";
	}

}
