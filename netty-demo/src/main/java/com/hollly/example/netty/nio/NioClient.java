package com.hollly.example.netty.nio;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hollly
 * @date 2021/10/24 11:39
 */
public class NioClient {

    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

            while (true) {

                // 一定那么
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                for (SelectionKey selectionKey : selectionKeys) {
                    if (selectionKey.isConnectable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        if (channel.isConnectionPending()) {
                            channel.finishConnect();

                            ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                            writeBuffer.put((LocalDateTime.now() + "连接成功").getBytes());
                            channel.write(writeBuffer);

                            // 起一个新的线程，读取操作台输入的数据，并发送给服务端
                            ExecutorService executorService = Executors.newSingleThreadExecutor();
                            executorService.execute(() -> {
                                while (true) {

                                    try {
                                        writeBuffer.clear();
                                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                                        String input = reader.readLine();

                                        writeBuffer.put(input.getBytes());
                                        writeBuffer.flip();
                                        channel.write(writeBuffer);

                                    } catch (IOException e) {
                                        e.printStackTrace();

                                    }
                                }
                            });
                            channel.register(selector, SelectionKey.OP_READ);
                        }
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int count = channel.read(buffer);
                        if (count  > 0) {
//                            buffer.flip();
//                            Charset charset = StandardCharsets.UTF_8;
//                            String value  = charset.decode(buffer).toString();
//                            System.out.println("server发送消息：" + value);

                            String s = new String(buffer.array(), 0, count);
                            System.out.println("server发送消息: " + s);
                        }

                    }
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
