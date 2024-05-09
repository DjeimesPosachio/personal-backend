package com.personal.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SecurityConfiguration {
    private static final String[] WHITE_LIST_ORIGIN = {"http://localhost:3001"};
    private final long MAX_AGE_SECS = 3600;

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(WHITE_LIST_ORIGIN)
                        .allowCredentials(true)
                        .allowedHeaders("Accept", "origin", "Authorization", "Content-Type", "Referer", "sec-ch-ua", "sec-ch-ua-mobile", "timezone", "User-Agent", "token", "Keep-Alive", "X-Requested-Wit")
                        .allowedMethods("HEAD", "PUT", "GET", "POST", "DELETE", "OPTIONS", "PATCH")
                        .maxAge(MAX_AGE_SECS);
            }
        };
    }
}
