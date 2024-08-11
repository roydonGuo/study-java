package com.roydon.bridge;

public class REVBBFile implements VideoFile {
    public void decode(String fileName) {
        System.out.println("rmvb文件：" + fileName);
    }
}
