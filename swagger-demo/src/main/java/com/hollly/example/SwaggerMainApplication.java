package com.hollly.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author hollly
 * @date 2022/8/20 19:54
 */
@EnableAsync
@SpringBootApplication
public class SwaggerMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerMainApplication.class, args);
    }
}
