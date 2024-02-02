package com.pathbreaker.hostinghub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication(scanBasePackages = {"com.pathbreaker.services.filters"})
public class HostingHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HostingHubApplication.class, args);
	}

}
