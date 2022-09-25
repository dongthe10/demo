package com.hollly.example.netty;

import com.hollly.example.netty.copy.MyClientInitailizer;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 简易netty客户端
 *
 *
 * @author hollly
 * @date 2021/11/28 16:36
 */
public class SimpleClient {

    private static final Logger log = Logger.getLogger(SimpleClient.class.getName());

    private static volatile boolean START_FLAG = false;

    public static void main(String[] args) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                // 客户端编码器和 服务端解码器要对应，不然服务端会收不到消息
                .handler(new MyClientInitailizer());

        try {
            ChannelFuture channelFuture = null;
            try {
                channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8899)).sync();
            } catch (InterruptedException e) {
                log.log(Level.SEVERE, "netty客户端连接异常");
                e.printStackTrace();
            }
            if (channelFuture.isSuccess()) {
                log.info("netty客户端成功连接");
            }
            Channel channel = channelFuture.channel();

            // 开启线程读取控制台信息
            new Thread(runnableTask(channel)).start();
            START_FLAG = true;
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }


    public static Runnable runnableTask(Channel channel) {
        return new Runnable() {
            Scanner scanner = new Scanner(System.in);

            @Override
            public void run() {
                while (START_FLAG) {
                    String line = scanner.nextLine();
                    ChannelFuture future = channel.writeAndFlush(Unpooled.wrappedBuffer(line.getBytes()));
                    log.info("成功发送消息 msg=" + line);
//                    future.addListener((ChannelFutureListener) channelFuture ->
//                            System.out.println("Registry cim server success!")
//                    );

                }

            }
        };
    }
}
