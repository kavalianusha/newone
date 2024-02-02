package com.pathbreaker.pmp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.pathbreaker"})
public class PmpApplication {

	public static void main(String[] args) {
		SpringApplication.run(PmpApplication.class, args);
	}

}
