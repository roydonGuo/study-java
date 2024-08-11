package com.roydon.join;

/**
 * ThreeThreadPrintTest
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/27
 * 三个线程顺序执行：t1->t2->t3
 **/
public class ThreeThreadPrintTest1 {
    public static void main(String[] args)  {
        Thread t1 = new Thread(() -> {
            System.out.println("t1开始执行。。。");
        });
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t2开始执行。。。");
        });
        Thread t3 = new Thread(() -> {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("t3开始执行。。。");
        });
        t1.start();
        t2.start();
        t3.start();

    }
}
