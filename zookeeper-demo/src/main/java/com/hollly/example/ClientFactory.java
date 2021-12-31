package com.hollly.example;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 创建curator客户端工厂
 *
 *
 * @author hollly
 * @date 2021/12/19 13:34
 */
public class ClientFactory {


    public static CuratorFramework createSimple(String connectionString) {
        ExponentialBackoffRetry policy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectionString, policy);
    }


    public static CuratorFramework createWithOptions(String connectionString, RetryPolicy policy,
                                                     int connectionTimeouts, int sessionTimeout) {
        return CuratorFrameworkFactory.builder()
                .connectString(connectionString)
                .retryPolicy(policy)
                .connectionTimeoutMs(connectionTimeouts)
                .sessionTimeoutMs(sessionTimeout)
                .build();
    }
}
