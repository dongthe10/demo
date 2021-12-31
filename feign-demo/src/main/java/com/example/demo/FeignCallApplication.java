package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableAsync
@EnableFeignClients
public class FeignCallApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignCallApplication.class, args);
    }

}
