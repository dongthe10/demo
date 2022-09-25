package com.hollly.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hollly
 * @date 2022/8/20 20:03
 */
@RequestMapping(value = "/demo")
@RestController
public class DemoController {

    @GetMapping("/test")
    public Object testMethod() {
        return "hello world";
    }

}
