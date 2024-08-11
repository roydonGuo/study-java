package com.roydon.aqs.semaphore;

import sun.misc.Unsafe;

import java.util.concurrent.Semaphore;

/**
 * SemaphoreDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 * 信号量
 **/
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println("running....");
                    Thread.sleep(1000);
                    System.out.println("finish...");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
