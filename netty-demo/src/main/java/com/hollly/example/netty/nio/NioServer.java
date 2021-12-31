package com.hollly.example.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author hollly
 * @date 2021/10/24 0:49
 */
public class NioServer {


    private static final Logger log =  Logger.getLogger(NioServer.class.getName());
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket socket = serverSocketChannel.socket();
        socket.bind(new InetSocketAddress("127.0.0.1", 8899));

        // 获取selector，将serverSocketChannel注册到selector
        Selector selector = Selector.open();
        SelectionKey register = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        HashMap<String, SocketChannel> clientMap = new HashMap<>();

        // 每个服务都有一个主循环，保证服务器保持运行
        while (true) {
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历selectionKeys
            selectionKeys.forEach(selectionKey -> {
                try {
                    // 此时selectionKey是serverSocketChannel注册的selectorKey
                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        try {
                            SocketChannel client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);

                            clientMap.put(UUID.randomUUID().toString(), client);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (selectionKey.isReadable()) {
                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        String  clientKey = null;
                        // 获取客户端信息
                        for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                            if (entry.getValue().equals(client)) {
                                clientKey = entry.getKey();
                            }
                        }
                        if (client == null) {
                            log.log(Level.SEVERE, "客户端获取clientKey出现异常");
                        } else {
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            int read = client.read(buffer);
                            if (read > 0) {

                                // 读取客户端的数据
                                buffer.flip();
                                Charset charset = StandardCharsets.UTF_8;
                                String value  = charset.decode(buffer).toString();
                                log.info(clientKey + "发送消息：" + value);
                                ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                writeBuffer.put(("已收到消息: " + value).getBytes());
                                writeBuffer.flip();
                                client.write(writeBuffer);
                            }
                        }
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }
            });


            // 消费完， 一定要清空，
            selectionKeys.clear();

        }

    }
}
