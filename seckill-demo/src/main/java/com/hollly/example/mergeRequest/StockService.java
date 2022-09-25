package com.hollly.example.mergeRequest;

import java.util.UUID;

/**
 * @author hollly
 * @date 2022/5/30 23:38
 */
public class StockService {



    public Result operate(RequestPromise requestPromise) {


        try {
            synchronized (requestPromise) {
                // 加入队列和等待需要放到 synchronized 中
                // 两个原因：requestPromise对象有并发问题，wait/notify需要放到synchronized里面
                String s = UUID.randomUUID().toString();
                System.out.println("放入前 " + s + ":" + System.currentTimeMillis());
                QueueManager.bQueue.add(requestPromise);
                requestPromise.wait(200);
                System.out.println("放入后 "+ s + ":" + System.currentTimeMillis());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        System.out.println(requestPromise.getResult());
        return requestPromise.getResult();
    }
}
