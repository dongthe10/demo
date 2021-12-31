package com.example.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @author hollly
 * @date 2020/12/19 23:31
 */
@Configuration
@RefreshScope
@Data
public class CustomConfig<List> {


    @Value("${user.nickname}")
    String userName;

    @Value("${user.age}")
    int age;
}
