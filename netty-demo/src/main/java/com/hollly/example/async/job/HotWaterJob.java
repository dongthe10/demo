package com.hollly.example.async.job;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 烧开水任务
 *
 * @author hollly
 * @date 2021/12/25 14:56
 */
public class HotWaterJob implements Callable<Boolean>, Runnable {

    @Override
    public Boolean call() {
        System.out.println("开始烧开水");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("烧开水完毕");
        return true;
    }

    @Override
    public void run() {
        this.call();
    }
}
