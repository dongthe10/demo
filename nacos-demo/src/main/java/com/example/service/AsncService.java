package com.example.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author hollly
 * @date 2020/12/24 0:25
 */
@Service
public class AsncService {

    @Async
    public void print() {
        try {
            TimeUnit.SECONDS.sleep(3);
            System.out.println("当前线程名字为：" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
