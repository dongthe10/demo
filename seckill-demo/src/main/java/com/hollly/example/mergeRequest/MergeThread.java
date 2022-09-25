package com.hollly.example.mergeRequest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author hollly
 * @date 2022/5/30 0:22
 */
public class MergeThread implements Runnable{

    private volatile int status = 0;


    private int stockNum = MergeRequestTest.stockNum;


    @Override
    public void run() {
        while (status == 0) {
            try {

                ArrayList<RequestPromise> list = new ArrayList<>();

                RequestPromise requestPromise = QueueManager.bQueue.poll(10, TimeUnit.MILLISECONDS);

                if (requestPromise == null) {
                    continue;
                } else {
                    list.add(requestPromise);
                }
                while (!QueueManager.bQueue.isEmpty()) {
                    list.add(QueueManager.bQueue.poll());
                }

                System.out.println(list.size());

                long count = list.stream().map(RequestPromise::getStockUpdateRequest).map(StockUpdateRequest::getCount).count();
                if (count < stockNum) {
                    for (RequestPromise promise : list) {
                        synchronized (promise) {
                            promise.setResult(new Result(0, "成功"));
                            stockNum -= promise.getStockUpdateRequest().getCount();
                            promise.notify();
                        }
                    }
                } else {
                    List<RequestPromise> sortRequestList = list.stream().sorted(Comparator.comparing(var -> var.getStockUpdateRequest().getCount()))
                            .collect(Collectors.toList());
                    for (RequestPromise promise : sortRequestList) {
                        if (stockNum < promise.getStockUpdateRequest().getCount()) {
                            promise.setResult(new Result(1, "失败"));
                        } else {
                            promise.setResult(new Result(0, "成功"));
                            stockNum -= promise.getStockUpdateRequest().getCount();
                        }
                        synchronized (promise) {
                            promise.notify();
                        }
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("队列处理请求出现异常");
            }
        }
    }



    public void start() {
        new Thread(this, "mergeThread").start();
    }


    public void stop() {
        status = 1;
    }
}
