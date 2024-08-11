package com.roydon.nio.section_01;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 * ByteBuffer配合FileChannel读取文件
 **/
@Slf4j
public class ByteBuffer1 {
    public static void main(String[] args) {
        // FileChannel
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(10);
            while (true) {
                int len = channel.read(byteBuffer);
                log.debug("读取到的字节数 {}", len);
                if (len == -1) break;
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    byte b = byteBuffer.get();
                    log.debug("读取到的字节 {}", (char) b);
                }
                byteBuffer.clear();
//                byteBuffer.compact();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
