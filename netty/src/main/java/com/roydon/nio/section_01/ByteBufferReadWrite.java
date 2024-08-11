package com.roydon.nio.section_01;

import com.roydon.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;

/**
 * ByteBufferReadWrite
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 * ByteBuffer 读写
 **/
public class ByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61); //a
        ByteBufferUtil.debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64}); // b c d
        ByteBufferUtil.debugAll(buffer);
    }
}
