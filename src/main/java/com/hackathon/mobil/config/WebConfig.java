package com.hackathon.mobil.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // API endpoint'inizin path pattern'ını belirtin
                .allowedOrigins("http://localhost:3000") // İzin verilen origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // İzin verilen HTTP metotları
                .allowedHeaders("Content-Type", "Authorization"); // İzin verilen başlıklar
    }
}