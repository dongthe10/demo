package com.hollly.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hollly
 * @date 2022/8/21 14:30
 */
@SpringBootApplication
@MapperScan("com.hollly.example.mapper")
public class OauthResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthResourceApplication.class, args);
    }
}