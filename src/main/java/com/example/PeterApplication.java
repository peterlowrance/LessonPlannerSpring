package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableJpaRepositories(basePackages="com.example.repository")
//@EntityScan(basePackages="com.example.models")
public class PeterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeterApplication.class, args);
    }
}
