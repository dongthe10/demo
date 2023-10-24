package com.hollly.example;

/**
 * @author hollly
 * @date 2023/10/1 15:11
 */

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @author liuhaodong
 * @date 2023/9/28 17:48
 */
public class DisruptorTest {

    public static void main(String[] args) throws InterruptedException {
        // 指定事件工厂
        LongEventFactory factory = new LongEventFactory();

        // 指定 ring buffer字节大小, 必须是2的N次方
        int bufferSize = 16;

        // 如果保证只是单线程发布事件，那么使用单线程模式，可以获取额外@的性能
        // 否则需要使用ProducerType.MULTI，不然会出现事件丢失
        // Executors.defaultThreadFactory() or DaemonThreadFactory.INSTANCE
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,
                bufferSize, Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy());
        //设置事件业务处理器---消费者
        disruptor.handleEventsWith(new LongEventHandler(), new LongEventHandlerBetween()).then(new LongEventHandler2());
        disruptor.handleEventsWith(new LongEventHandlerOther(), new LongEventHandlerOther2());
        disruptor.handleEventsWithWorkerPool((event) -> {
            System.out.println("handleEventsWithWorkerPool consumer:" + Thread.currentThread().getName() + " Event: value=" + event.getValue());
        }, (event) -> {
            System.out.println("handleEventsWithWorkerPool consumer:" + Thread.currentThread().getName() + " Event: value=" + event.getValue());
        }).thenHandleEventsWithWorkerPool((e) -> {
            System.out.println("then handleEventsWithWorkerPool 1 consumer:" + Thread.currentThread().getName() + " Event: value=" + e.getValue());
        }, (e) -> {
            System.out.println("then handleEventsWithWorkerPool 2 consumer:" + Thread.currentThread().getName() + " Event: value=" + e.getValue());
        });

        //启动disruptor线程
        disruptor.start();


        // 获取 ring buffer环，用于接取生产者生产的事件
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        //为 ring buffer指定事件生产者
//        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
//        new Thread(() -> {
//            for (int i = 0; i < 33; i++) {
//                //获取一个随机数
//                long value = (long) ((Math.random() * 1000000) + 1);
//                //发布数据
//                ringBuffer.publishEvent((event, sequence, arg0) -> event.set(arg0), value);
//            }
//        }).start();
//        new Thread(() -> {
//            for (int i = 0; i < 33; i++) {
//                //获取一个随机数
//                long value = (long) ((Math.random() * 1000000) + 1);
//                //发布数据
//                ringBuffer.publishEvent((event, sequence, arg0) -> event.set(arg0), value);
//            }
//        }).start();
//        new Thread(() -> {
//            for (int i = 0; i < 33; i++) {
//                //获取一个随机数
//                long value = (long) ((Math.random() * 1000000) + 1);
//                //发布数据
//                ringBuffer.publishEvent((event, sequence, arg0) -> event.set(arg0), value);
//            }
//        }).start();
        //循环遍历
        for (int i = 0; i < 20; i++) {
            //获取一个随机数
            long value = (long) ((Math.random() * 1000000) + 1);
            //发布数据
            ringBuffer.publishEvent((event, sequence, arg0) -> event.set(arg0), value);
        }
//        TimeUnit.SECONDS.sleep(10000l);
        //停止disruptor线程
        disruptor.shutdown();

    }
}
