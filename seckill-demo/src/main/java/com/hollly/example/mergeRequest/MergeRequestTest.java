package com.hollly.example.mergeRequest;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 合并请求测试
 *
 *
 * @author hollly
 * @date 2022/5/29 23:41
 */
public class MergeRequestTest {


    /**
     * 库存数量
     */
    public static final Integer stockNum = 6;


    private static ScheduledExecutorService executor = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());


    public static void main(String[] args) throws InterruptedException {
        // 准备上下文
        start();
        TimeUnit.SECONDS.sleep(2);

        StockService stockService = new StockService();

        ArrayList<Future> futures = new ArrayList<>();
        // 模拟并发请求数量
        int requestNum = 10;
        for (int i = 0; i < requestNum; i++) {
            StockUpdateRequest stockUpdateRequest = new StockUpdateRequest((long) i, (long) i, 1);
            Future<?> future = executor.submit(() -> {
                return stockService.operate(new RequestPromise(stockUpdateRequest));
            });
            futures.add(future);
        }

        for (Future future : futures) {
            try {
                Object o = future.get();
                System.out.println(o);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }




    }

    private static void start() {
        new MergeThread().start();
    }

}
