package com.hollly.example.mergeRequest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 队列管理器
 *
 * @author hollly
 * @date 2022/5/30 0:27
 */
public class QueueManager {


    public static final BlockingQueue<RequestPromise> bQueue = new LinkedBlockingQueue<>();
}
