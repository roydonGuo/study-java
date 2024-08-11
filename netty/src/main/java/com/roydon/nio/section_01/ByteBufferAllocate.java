package com.roydon.nio.section_01;

import java.nio.ByteBuffer;

/**
 * ByteBufferAllocate
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 **/
public class ByteBufferAllocate {
    public static void main(String[] args) {
        System.out.println(ByteBuffer.allocate(16).getClass());  // HeapByteBuffer 堆内存
        System.out.println(ByteBuffer.allocateDirect(16).getClass()); // DirectByteBuffer 直接内存
    }
}
