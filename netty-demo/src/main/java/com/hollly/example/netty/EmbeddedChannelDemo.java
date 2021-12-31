package com.hollly.example.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;

import java.nio.charset.Charset;

/**
 * @author hollly
 * @date 2021/12/26 17:49
 */
public class EmbeddedChannelDemo {


    private static class SimpleHandlerA extends ChannelDuplexHandler {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("A 处理中");
            super.channelRead(ctx, msg);
        }
    }
    private static class SimpleHandlerB extends ChannelDuplexHandler {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("B 处理中");
            super.channelRead(ctx, msg);
        }
    }
    private static class SimpleHandlerC extends ChannelDuplexHandler {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("C 处理中");
            System.out.println(msg);
            super.channelRead(ctx, msg);
        }
    }


    public static void main(String[] args) {
        ChannelInitializer<EmbeddedChannel> channelInitializer = new ChannelInitializer<EmbeddedChannel>() {

            @Override
            protected void initChannel(EmbeddedChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleHandlerA());
                ch.pipeline().addLast(new SimpleHandlerB());
                ch.pipeline().addLast(new SimpleHandlerC());
            }
        };

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(channelInitializer);

        embeddedChannel.writeInbound(23242);


        ByteBuf buffer = Unpooled.buffer();
        buffer.writeCharSequence("fsfsefwefewfwef", Charset.forName("UTF-8"));
        buffer.retain();
    }
}
