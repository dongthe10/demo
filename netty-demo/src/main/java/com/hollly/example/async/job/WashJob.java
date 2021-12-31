package com.hollly.example.async.job;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 洗碗任务
 *
 * @author hollly
 * @date 2021/12/25 14:56
 */
public class WashJob implements Callable<Boolean>, Runnable {

    @Override
    public Boolean call() {
        System.out.println("开始洗碗");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("洗碗完毕");
        return true;
    }

    @Override
    public void run() {
        this.call();
    }
}
