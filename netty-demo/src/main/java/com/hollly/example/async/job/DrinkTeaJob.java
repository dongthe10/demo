package com.hollly.example.async.job;

import java.util.concurrent.Callable;

/**
 * @author hollly
 * @date 2021/12/25 14:57
 */
public class DrinkTeaJob implements Callable<Boolean>, Runnable {
    @Override
    public void run() {
        this.call();
    }

    @Override
    public Boolean call(){
        System.out.println("准备完毕，开始喝茶");
        return true;
    }
}
