package com.hollly.example.netty.copy;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class MyClient {

    private static boolean START_FLAG = false;

    public static void main(String[] args){
        EventLoopGroup  eventLoopGroup = new NioEventLoopGroup();
        ChannelFuture channelFuture = null;
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new MyClientInitailizer());
            channelFuture = bootstrap.connect("localhost",8899).sync();
        } catch (Exception e){
            e.printStackTrace();
        }
//        finally {
//            eventLoopGroup.shutdownGracefully();
//        }

        // 开启线程读取控制台信息
        new Thread(runnableTask((SocketChannel) channelFuture.channel())).start();
        START_FLAG = true;
    }

    public static Runnable runnableTask(SocketChannel channel) {
        return new Runnable() {
            Scanner scanner = new Scanner(System.in);

            @Override
            public void run() {
                while (START_FLAG) {
                    String line = scanner.nextLine();
                    ByteBuf byteBuf = Unpooled.copiedBuffer(line.getBytes());
                    System.out.println(channel.isWritable());
                    System.out.println(channel.isOpen());
                    ChannelFuture future = channel.writeAndFlush(byteBuf);
                    System.out.println("成功发送消息 msg=" + future.isSuccess());
//                    future.addListener((ChannelFutureListener) channelFuture ->
//                            System.out.println("Registry cim server success!")
//                    );

                }

            }
        };
    }

}
