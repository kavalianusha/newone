package com.example.springproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SpringSecurityBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBasicsApplication.class, args);
	}

}
