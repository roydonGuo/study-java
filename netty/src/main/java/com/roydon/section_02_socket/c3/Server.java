package com.roydon.section_02_socket.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.roydon.util.ByteBufferUtil.debugAll;
import static com.roydon.util.ByteBufferUtil.debugRead;

/**
 * Server
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 * Selector
 **/
@Slf4j
public class Server {

    private static void split(ByteBuffer source) {
        source.flip();
        int oldLimit = source.limit();
        for (int i = 0; i < oldLimit; i++) {
            if (source.get(i) == '\n') {
//                System.out.println(i);
                ByteBuffer target = ByteBuffer.allocate(i + 1 - source.position());
                // 0 ~ limit
                source.limit(i + 1);
                target.put(source); // 从source 读，向 target 写
                debugAll(target);
                source.limit(oldLimit);
            }
        }
        source.compact();
    }

    public static void main(String[] args) throws Exception {
        // 1、Selector.open()
        Selector selector = Selector.open();

        ByteBuffer buffer = ByteBuffer.allocate(16);
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        // 2、注册channel,SelectionKey得到事件channel与哪个事件
        SelectionKey sscKey = ssc.register(selector, 0, null);
        sscKey.interestOps(SelectionKey.OP_ACCEPT);// SelectionKey对什么事件感兴趣

        ssc.bind(new InetSocketAddress(8088));

        while (true) {
            // select()无事发生就阻塞，有事发生就继续
            selector.select();
            // 处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove(); // 一定要手动移除
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel accept = channel.accept();
                    log.debug("{}", accept);
                    accept.configureBlocking(false);
                    ByteBuffer buffer1 = ByteBuffer.allocate(16);
                    SelectionKey scKey = accept.register(selector, 0, buffer1);
                    scKey.interestOps(SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    try {
                        SocketChannel channel = (SocketChannel) key.channel();
                        ByteBuffer bf = (ByteBuffer) key.attachment();
                        int read = channel.read(bf);// 正常断开read为-1
                        if (read == -1) {
                            key.cancel();
                        } else {
                            split(bf);
//                            buffer1.flip();
////                            debugRead(buffer1);
//                            System.out.println(Charset.defaultCharset().decode(buffer1));
                            if (bf.position() == bf.limit()) {
                                ByteBuffer newBuffer = ByteBuffer.allocate(buffer.capacity() * 2);
                                bf.flip();
                                newBuffer.put(bf);
                                key.attach(newBuffer);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                }

            }
        }
    }
}
