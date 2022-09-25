package com.hollly.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author hollly
 * @date 2022/6/11 23:03
 */
@RestController
@RequestMapping("/sentinel")
public class FlowLimitController {


    @GetMapping("/test")
    public Object test() {
        return "i am fine, test success";
    }

    @GetMapping("/circuitBreaker")
    public Object circuitBreaker() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "i am fine, test success";
    }
}
