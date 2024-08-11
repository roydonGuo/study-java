package com.roydon.nio.section_01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * FileTreeCopy
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 * copy多级目录
 **/
public class WalkFileTreeCopy {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        String source = "E:\\baidu\\BaiduNetdiskDownload\\2、黑马程序员Java项目《学成在线》企业级开发实战\\学成在线项目—视频\\day01 项目介绍&环境搭建";
        String target = "E:\\baidu\\BaiduNetdiskDownload\\2、黑马程序员Java项目《学成在线》企业级开发实战\\学成在线项目—视频\\day01 项目介绍&环境搭建-copy";
        Files.walk(Paths.get(source)).forEach(path -> {
            try {
                String targetName = path.toString().replace(source, target);
                // 是目录
                if (Files.isDirectory(path)) {
                    Files.createDirectory(Paths.get(targetName));
                }
                // 是普通文件
                else if (Files.isRegularFile(path)) {
                    Files.copy(path, Paths.get(targetName));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
