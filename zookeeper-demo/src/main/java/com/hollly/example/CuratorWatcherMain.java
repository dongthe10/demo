package com.hollly.example;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 测试curator两种watcher
 *
 * 一、标准的观察者模式监听
 *
 * 二、Cache事件监听 分为三种类型，NodeCache，PathCache TreeCache
 *
 * Node Cache 可用于ZNode节点的监听
 * Path Cache 可用于ZNode子节点的监听
 * Tree Cache 可用于ZNode节点 和 其子节点的监听
 *
 *
 * @author hollly
 * @date 2021/12/19 17:12
 */
public class CuratorWatcherMain {

    private static final String ADDRESS = "127.0.0.1:2181";

    private static final String ZK_PATH = "/test/WATCHER/node-1";
    private static final String ZK_PATH_PARENT = "/test/WATCHER";
    private static final String ZK_PATH_PARENT_PREFIX = "/test/WATCHER/node-";

    public static void main(String[] args) throws Exception {
//        normalWatcher();

//        nodeCacheWatcher();

        pathCacheWatcher();

//        treeCacheWatcher();
    }


    /**
     * 普通注册时间，每次注册只会生效一次
     * @throws Exception
     */
    public static void normalWatcher() throws Exception {
        CuratorFramework client = ClientFactory.createSimple(ADDRESS);
        client.start();

        if (!existNode(client, ZK_PATH)) {
            System.out.println("节点不存在");
            client.create().creatingParentsIfNeeded().forPath(ZK_PATH);
            return;
        }
        Watcher watcher = new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                System.out.println(event);
            }
        };

        try {
            // getData() getChildren() getExists() 都能使用注册普通监听事件
            client.getData().usingWatcher(watcher).forPath(ZK_PATH);
            client.setData().forPath(ZK_PATH);
            client.setData().forPath(ZK_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void nodeCacheWatcher() {

        try {
            CuratorFramework client = ClientFactory.createSimple(ADDRESS);
            client.start();

            NodeCache nodeCache = new NodeCache(client, ZK_PATH);


            NodeCacheListener nodeCacheListener = new NodeCacheListener() {

                @Override
                public void nodeChanged() throws Exception {
                    System.out.println(nodeCache.getPath() + " " + new String(nodeCache.getCurrentData().getData(), "UTF-8"));
                }
            };


            nodeCache.getListenable().addListener(nodeCacheListener);
            nodeCache.start();
            // 可以设置是否在启动时，立即调用获取数据，即nodeCache.getCurrentData()可以立即获取到值
//            nodeCache.start(true);

            System.out.println("准备开始");
            TimeUnit.SECONDS.sleep(1);

            client.setData().forPath(ZK_PATH);
            TimeUnit.SECONDS.sleep(1);

            client.setData().forPath(ZK_PATH, "第二次更改内容".getBytes());
            TimeUnit.SECONDS.sleep(1);
            client.setData().forPath(ZK_PATH, "第三次更改内容".getBytes());
            TimeUnit.SECONDS.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pathCacheWatcher() {
        try {
            CuratorFramework client = ClientFactory.createSimple(ADDRESS);
            client.start();

            // 构造器和NodeCache有所区别
            PathChildrenCache pathChildrenCache = new PathChildrenCache(client, ZK_PATH_PARENT, true);


            PathChildrenCacheListener listener = new PathChildrenCacheListener() {


                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    ChildData data = event.getData();
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            System.out.println("子节点增加 path=" + data.getPath() +
                                    " data=" + new String(data.getData(), "UTF-8"));
                        case CHILD_UPDATED:
                            System.out.println("子节点修改 path=" + data.getPath() +
                                    " data=" + new String(data.getData(), "UTF-8"));
                        case CHILD_REMOVED:
                            System.out.println("子节点删除 path=" + data.getPath() +
                                    " data=" + new String(data.getData(), "UTF-8"));

                    }
                }
            };


            pathChildrenCache.getListenable().addListener(listener);

            // 包含三种模式
            pathChildrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
            // 可以设置是否在启动时，立即调用获取数据，即nodeCache.getCurrentData()可以立即获取到值
//            nodeCache.start(true);

            System.out.println("准备开始");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("开始修改节点");

            for (int i = 2; i <= 4; i++) {
                client.create().creatingParentsIfNeeded()
                        .forPath(ZK_PATH_PARENT_PREFIX + i);

            }
            System.out.println("开始删除节点");
            for (int i = 2; i <= 4; i++) {
                client.delete().forPath(ZK_PATH_PARENT_PREFIX + i);

            }


            TimeUnit.SECONDS.sleep(100);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void treeCacheWatcher() {
        try {
            CuratorFramework client = ClientFactory.createSimple(ADDRESS);
            client.start();

            // 构造器和NodeCache有所区别
            TreeCache pathChildrenCache = new TreeCache(client, ZK_PATH_PARENT);


            TreeCacheListener listener = new TreeCacheListener() {


                @Override
                public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                    ChildData data = event.getData();
                    switch (event.getType()) {
                        case NODE_ADDED:
                            System.out.println("节点增加 path=" + data.getPath() +
                                    " data=" + new String(data.getData(), "UTF-8"));
                        case NODE_UPDATED:
                            System.out.println("节点修改 path=" + data.getPath() +
                                    " data=" + new String(data.getData(), "UTF-8"));
                        case NODE_REMOVED:
                            System.out.println("节点删除 path=" + data.getPath() +
                                    " data=" + new String(data.getData(), "UTF-8"));

                    }
                }
            };


            pathChildrenCache.getListenable().addListener(listener);

            pathChildrenCache.start();

            System.out.println("准备开始");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("开始修改节点");

            for (int i = 2; i <= 4; i++) {
                client.create().creatingParentsIfNeeded()
                        .forPath(ZK_PATH_PARENT_PREFIX + i);

            }

            TimeUnit.SECONDS.sleep(1);
            System.out.println("开始删除节点");
            for (int i = 2; i <= 4; i++) {
                client.delete().forPath(ZK_PATH_PARENT_PREFIX + i);

            }
            client.delete().deletingChildrenIfNeeded().forPath(ZK_PATH_PARENT);

            TimeUnit.SECONDS.sleep(100);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    private static boolean existNode(CuratorFramework client, String zkPath) {
        try {
            return client.checkExists().forPath(zkPath) != null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
