package com.hollly.example.controller;

import com.hollly.example.api.GreetingService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hollly
 * @date 2022/6/3 14:29
 */
@RequestMapping("/hello")
@RestController
public class HelloController {


    private static Logger log = LoggerFactory.getLogger(HelloController.class);

    @DubboReference
    private GreetingService greetingService;


    @PostMapping("/greeting")
    public Object greeting() {
        String hello = greetingService.speaking();
        log.info("Hello: " + hello);

        boolean b = greetingService.handleShake();
        log.info("handleShake: " + b);


        return hello + " ----- " + b;
    }
}
