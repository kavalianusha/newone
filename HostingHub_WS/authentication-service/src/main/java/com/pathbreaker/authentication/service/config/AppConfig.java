package com.pathbreaker.authentication.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties(prefix = "service")
public class AppConfig {
    private String hostingHubUserNameUri;
}
