package com.roydon;

/**
 * demo1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/7
 * synchronized代码块解决活锁问题
 **/
public class Demo1 {

    static int count = 0;
    static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                synchronized (lock) {
                    count++;
                    System.out.println("count = " + count);
                }
            }

        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                synchronized (lock) {
                    count--;
                    System.out.println("count = " + count);
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

    }

}
