package com.hollly.example.netty;

import io.netty.bootstrap.Bootstrap;
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
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("client handlerAdded");
                            }

                            @Override
                            public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
                                System.out.println("client handlerRemoved");
                            }

                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                System.out.println("client exceptionCaught");
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                System.out.println("channelRead " + msg);
                                super.channelRead(ctx, msg);
                            }
                        });
                    }
                });


        ChannelFuture channelFuture = null;
        try {
            channelFuture = bootstrap.connect(new InetSocketAddress("localhost", 8080)).sync();
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

    }


    public static Runnable runnableTask(Channel channel) {
        return new Runnable() {
            Scanner scanner = new Scanner(System.in);

            @Override
            public void run() {
                while (START_FLAG) {
                    String line = scanner.nextLine();
                    ChannelFuture future = channel.writeAndFlush(line);
                    log.info("成功发送消息 msg=" + line);
                    future.addListener((ChannelFutureListener) channelFuture ->
                            System.out.println("Registry cim server success!")
                    );

                }

            }
        };
    }
}
