package com.example.efilingbazaar;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.efilingbazaar.repository")
@EntityScan(basePackages = "com.example.efilingbazaar.entity")
public class EfilingBazaarProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EfilingBazaarProjectApplication.class, args);
	}
	@Bean
    public WebMvcConfigurer configure() {
        return new WebMvcConfigurer() {
            
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/*").allowedOrigins("*");
                
            }
        };
	}
	
	

}
