package com.altran.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class CORSConfiguration {

    @Bean
    public WebMvcConfigurer corsConfiguration(){

        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedHeaders("content-type", "Content-Type","Origin","X-Requested-With","Accept","Authorization", "Cache-Control")
                        .allowedMethods("POST, GET, PUT, DELETE, OPTIONS, HEAD")
                        .maxAge(TimeUnit.DAYS.toSeconds(1))
                ;
            }
        };

    }


}
