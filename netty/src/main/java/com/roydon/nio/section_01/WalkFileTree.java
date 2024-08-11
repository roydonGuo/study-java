package com.roydon.nio.section_01;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WalkFileTree
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/23
 * 遍历文件树
 **/
public class WalkFileTree {
    public static void main(String[] args) throws IOException {

        AtomicInteger dirCount = new AtomicInteger();
        AtomicInteger fileCount = new AtomicInteger();

        Files.walkFileTree(Paths.get("E:\\baidu\\BaiduNetdiskDownload\\Netty网络编程\\Netty教程源码资料"), new SimpleFileVisitor<Path>() {
            /**
             * Invoked for a directory before entries in the directory are visited.
             *
             * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
             * CONTINUE}.
             *
             * @param dir
             * @param attrs
             */
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("dir =====> " + dir);
                dirCount.incrementAndGet();
                // 若是删除文件夹需要确保文件夹下内容为空否则报错
                return super.preVisitDirectory(dir, attrs);
            }

            /**
             * Invoked for a file in a directory.
             *
             * <p> Unless overridden, this method returns {@link FileVisitResult#CONTINUE
             * CONTINUE}.
             *
             * @param file
             * @param attrs
             */
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("file = " + file);
                fileCount.incrementAndGet();
                // 统计md文档的数量
                if (file.toFile().getName().endsWith("nio.md")) {
//                    fileCount.incrementAndGet();
//                    Files.delete(file);
                }
                return super.visitFile(file, attrs);
            }
        });
        System.out.println(dirCount);
        System.out.println(fileCount);
    }
}
