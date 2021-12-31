package com.hollly.example;

import com.alipay.remoting.rpc.RpcServer;
import com.alipay.sofa.jraft.*;
import com.alipay.sofa.jraft.conf.Configuration;
import com.alipay.sofa.jraft.entity.PeerId;
import com.alipay.sofa.jraft.entity.Task;
import com.alipay.sofa.jraft.option.NodeOptions;
import com.alipay.sofa.jraft.rpc.RaftRpcServerFactory;
import com.alipay.sofa.jraft.util.Endpoint;
import org.omg.CORBA.NO_IMPLEMENT;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;


/**
 * @author hollly
 * @date 2021/10/7 15:21
 */
public class JRaftMain {

    public static void main(String[] args) throws InterruptedException {
        Endpoint addr = JRaftUtils.getEndPoint("localhost:8080");
        Endpoint rpcAddr = JRaftUtils.getEndPoint("localhost:8081");



        PeerId peer = JRaftUtils.getPeerId("localhost:8080");
// 三个节点组成的 raft group 配置，注意节点之间用逗号隔开
        Configuration conf = JRaftUtils.getConfiguration("localhost:8081,localhost:8082,localhost:8083");

        // 添加节点
        NodeManager.getInstance().addAddress(addr);

        String groupId = "groupId-one";
        Node raftNode = RaftServiceFactory.createRaftNode(groupId, peer);
        NodeOptions opts = initOptions();
        if(!raftNode.init(opts)) {
            throw new IllegalStateException("启动 raft 节点失败，具体错误信息请参考日志。");
        }


        // 启动 raftNode的rpc服务
        RpcServer rpcServer = RaftRpcServerFactory.createAndStartRaftRpcServer(rpcAddr);

        // 同时提供 RPC 服务给用户使用
//        RaftRpcServerFactory.addRaftRequestProcessors(rpcServer);
//        rpcServer.init();

//        TimeUnit.SECONDS.sleep(10);
        autoAddTask(raftNode);

    }

    private static void autoAddTask(Node raftNode) {


        Closure done = x -> {

            System.out.println("fff");
            System.out.println(x);
        };
        Task task = new Task();
        task.setData(ByteBuffer.wrap("hello".getBytes()));
        task.setDone(done);
        raftNode.apply(task);
    }

    private static NodeOptions initOptions() {

        NodeOptions nodeOptions = new NodeOptions();
        // 设置状态机
        TestStateMachine stateMachine = new TestStateMachine();
        nodeOptions.setFsm(stateMachine);
        // 设置日志保存路径
        nodeOptions.setLogUri("E:\\Save\\logs\\jraft\\log");
        // 设置元信息存储位置
        nodeOptions.setRaftMetaUri("E:\\Save\\logs\\jraft\\meta");
        return nodeOptions;
    }


    /**
     * 任务
     * @return
     */
    private static Task task() {
        Closure done = (x) -> System.out.println("fff");
        Task task = new Task();
        task.setData(ByteBuffer.wrap("hello".getBytes()));
        task.setDone(done);
        return task;
    }
}
