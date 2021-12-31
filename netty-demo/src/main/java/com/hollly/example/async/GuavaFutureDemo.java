package com.hollly.example.async;

import com.google.common.util.concurrent.*;
import com.hollly.example.async.job.HotWaterJob;
import com.hollly.example.async.job.WashJob;

import java.util.concurrent.*;

/**
 * @author hollly
 * @date 2021/12/25 14:36
 */
public class GuavaFutureDemo {


    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();


        ListeningExecutorService moreExecutors = MoreExecutors.listeningDecorator(executor);

        MainJob mainJob = new MainJob();

        ListenableFuture<Boolean> listenableFuture = moreExecutors.submit((Callable<Boolean>) new HotWaterJob());
        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                System.out.println("标志烧水完成");
                mainJob.hotWaterFinish = true;
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("fail");
            }
        }, moreExecutors);

        ListenableFuture<Boolean> listenableFuture2 = moreExecutors.submit((Callable<Boolean>) new WashJob());
        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {

            @Override
            public void onSuccess(Boolean result) {
                System.out.println("标志洗碗完成");
                mainJob.washFinish = true;
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("fail2");
            }
        }, moreExecutors);

        mainJob.run();

    }

    private static class MainJob implements Runnable{
        boolean hotWaterFinish = false;
        boolean washFinish = false;

        @Override
        public void run() {
            while (true) {

                System.out.println("小睡一会 或 do other thing");
                try {
                    TimeUnit.MILLISECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (hotWaterFinish && washFinish) {
                    System.out.println("开始喝茶");
                    return;
                }
            }
        }

    }


}
