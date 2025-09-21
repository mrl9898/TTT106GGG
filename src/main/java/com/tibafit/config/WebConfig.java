package com.tibafit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 全站跨域
                        .allowedOrigins("*") // 允許任意前端域名
                        .allowedMethods("GET","POST","PUT","DELETE","OPTIONS") // 允許的 HTTP 方法
                        .allowedHeaders("*") // 允許所有 header
                        .allowCredentials(false); // 是否允許傳 cookie
            }
        };
    }
}
