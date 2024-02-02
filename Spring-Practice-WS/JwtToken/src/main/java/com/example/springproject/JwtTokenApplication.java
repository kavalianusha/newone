package com.example.springproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;


@SpringBootApplication
@ComponentScan(basePackages = "com.example.springproject")
public class JwtTokenApplication {
	public static void main(String[] args) {
		SpringApplication.run(JwtTokenApplication.class, args);
	}
}

