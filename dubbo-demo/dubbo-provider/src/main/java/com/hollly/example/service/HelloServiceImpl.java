package com.hollly.example.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.hollly.example.api.GreetingService;

/**
 * @author hollly
 * @date 2022/6/3 14:12
 */
//@org.springframework.stereotype.Service
@Service
public class HelloServiceImpl implements GreetingService {

    static {
        System.out.println("HelloServiceImpl class load");
    }

    @Override
    public String speaking() {
        return "hello";
    }

    @Override
    public boolean handleShake() {
        return true;
    }
}
