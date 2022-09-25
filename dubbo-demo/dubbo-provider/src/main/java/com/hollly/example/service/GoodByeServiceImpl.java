package com.hollly.example.service;

import com.hollly.example.api.GreetingService;

/**
 * @author hollly
 * @date 2022/7/16 19:24
 */
public class GoodByeServiceImpl implements GreetingService {

    static {
        System.out.println("GoodByeServiceImpl class load");
    }

    @Override
    public String speaking() {
        return "good bye";
    }

    @Override
    public boolean handleShake() {
        return false;
    }
}
