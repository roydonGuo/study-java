package com.roydon.nio.section_02_socket.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
//        sc.write(Charset.defaultCharset().encode("hello\nworld\n"));
        System.out.println("请输入：");
        while (scanner.hasNext()) {
            System.out.println("请输入：");
            sc.write(Charset.defaultCharset().encode(scanner.next() + "\n"));
        }
    }
}
