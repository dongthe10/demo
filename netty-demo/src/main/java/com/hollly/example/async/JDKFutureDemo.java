package com.hollly.example.async;

import com.hollly.example.async.job.DrinkTeaJob;
import com.hollly.example.async.job.HotWaterJob;
import com.hollly.example.async.job.WashJob;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @author hollly
 * @date 2021/12/25 14:35
 */
public class JDKFutureDemo {


    public static void main(String[] args) {

        FutureTask<Boolean> futureTask1 = new FutureTask<>(new HotWaterJob());
        FutureTask<Boolean> futureTask2 = new FutureTask<>(new WashJob());

        new Thread(futureTask1, "HotWaterJob Thread").start();
        new Thread(futureTask2, "WashJob Thread").start();

        Thread.currentThread().setName("Mann Thread");
        try {
            futureTask1.get();
            futureTask2.get();

            new DrinkTeaJob().run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
