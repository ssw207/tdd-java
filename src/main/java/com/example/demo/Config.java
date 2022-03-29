package com.example.demo;

import com.example.demo.service.PasswordValidator;
import com.example.demo.service.SimplePasswordValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public PasswordValidator passwordValidator() {
        return new SimplePasswordValidator();
    }
}
