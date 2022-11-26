package com.hollly.example.service;

import com.hollly.example.api.GreetingService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author hollly
 * @date 2022/6/3 14:12
 */
@org.springframework.stereotype.Service
@DubboService
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
