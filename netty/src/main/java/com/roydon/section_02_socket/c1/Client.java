package com.roydon.section_02_socket.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Client
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 **/
@Slf4j
public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 8088));
        log.info("wait...");
    }
}
