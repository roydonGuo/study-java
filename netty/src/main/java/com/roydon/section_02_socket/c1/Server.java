package com.roydon.section_02_socket.c1;

import com.roydon.util.ByteBufferUtil;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Server
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 * 使用nio来理阻塞模式，单线程
 **/
@Slf4j
public class Server {
    public static void main(String[] args) throws Exception {
        //1、创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        // 2、绑定端口
        ssc.bind(new InetSocketAddress(8088));
        // 3、连接集合
        List<SocketChannel> channels = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        while (true) {
            // 4、accept建立与客户端连接，SocketChannel用来与客户端通信
            log.debug("connecting...");
            SocketChannel sc = ssc.accept();
            log.debug("connected to: {}", sc);
            channels.add(sc);
            for (SocketChannel channel : channels) {
                log.debug("before read: {}", channel);
                channel.read(buffer);
                buffer.flip();
                ByteBufferUtil.debugRead(buffer);
                buffer.clear();
                log.debug("after read: {}", channel);
            }
        }

    }
}
