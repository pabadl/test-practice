package com.test_practice;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
      // Use allowedOriginPatterns for better matching (works with credentials)
      .allowedOriginPatterns("http://localhost:5173")
      .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
      .allowedHeaders("*")
      .exposedHeaders("Location", "Authorization")
      .allowCredentials(true)
      .maxAge(3600);
  }
}
