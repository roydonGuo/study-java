package com.roydon.nio.section_01;

import com.roydon.nio.util.ByteBufferUtil;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * ByteBuffer2Str
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 **/
public class ByteBuffer2Str {
    public static void main(String[] args) {
        ByteBuffer buffer1 = StandardCharsets.UTF_8.encode("你好");
        ByteBuffer buffer2 = Charset.forName("utf-8").encode("你好");

        ByteBufferUtil.debugAll(buffer1);
        ByteBufferUtil.debugAll(buffer2);

        CharBuffer buffer3 = StandardCharsets.UTF_8.decode(buffer1);
        System.out.println(buffer3.getClass());
        System.out.println(buffer3.toString());
    }
}
