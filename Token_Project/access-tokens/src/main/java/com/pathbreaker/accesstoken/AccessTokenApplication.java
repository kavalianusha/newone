package com.pathbreaker.accesstoken;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pathbreaker"})
public class AccessTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccessTokenApplication.class, args);
	}

}
