package com.hollly.example;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.UnsupportedEncodingException;

/**
 * @author hollly
 * @date 2021/12/19 13:41
 */
public class CuratorCRUDMain {

    private static final String ADDRESS = "localhost:2181";

    private static final String ZK_PATH = "/test/CRUD/node-1";
    private static final String ZK_PATH_PARENT = "/test/CRUD";



    public static void main(String[] args) {


//        createNode();
//
        readNode();

//        updateNode();

//        deleteNode();
    }


    public static void createNode() {
        CuratorFramework client = ClientFactory.createSimple(ADDRESS);
        try {
            client.start();
            String data = "hello";
            ;

            if (existNode(client, ZK_PATH)) {
                System.out.println("已存在当前节点");
                return ;
            }

            client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(ZK_PATH, data.getBytes("UTF-8"));
            System.out.println("创建成功");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void readNode() {
        CuratorFramework client = ClientFactory.createSimple(ADDRESS);

        try {
            client.start();
            Stat stat = client.checkExists().forPath(ZK_PATH);
            if (null != stat) {

                byte[] bytes = client.getData().forPath(ZK_PATH);
                String s = new String(bytes, "UTF-8");
                System.out.println("结果值为：" + s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void updateNode() {
        CuratorFramework client = ClientFactory.createSimple(ADDRESS);

        try {
            client.start();
            if (!existNode(client, ZK_PATH)) {
                System.out.println("节点不存在");
                return ;
            }

            client.setData().forPath(ZK_PATH, "hello world".getBytes("UTF-8"));
            System.out.println("修改成功");


            AsyncCallback.StringCallback callback = new AsyncCallback.StringCallback() {

                @Override
                public void processResult(int rc, String path, Object ctx, String name) {
                    System.out.println(rc);
                    System.out.println(path);
                    System.out.println(ctx);
                    System.out.println(name);
                }
            };
            client.setData()
                    .inBackground(callback)
                    .forPath(ZK_PATH, "hello world two".getBytes("UTF-8"));

            Thread.sleep(10000);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void deleteNode() {
        CuratorFramework client = ClientFactory.createSimple(ADDRESS);

        try {
            client.start();
            client.delete().deletingChildrenIfNeeded().forPath(ZK_PATH_PARENT);

            System.out.println("删除成功");
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
