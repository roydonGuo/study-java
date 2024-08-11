package com.roydon.thread;

/**
 * CountThreadDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/8
 **/
public class CountThreadDemo {
    private static Integer count = 0;
    private static final Integer THREAD_COUNT = 5;
    private static final Integer THREAD_PER_COUNT = 20000;

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[THREAD_COUNT];

        for (int i = 0; i < THREAD_COUNT; i++) {

           threads[i] =  new Thread(() -> {
                for (int j = 0; j < THREAD_PER_COUNT; j++) {
                    synchronized (CountThreadDemo.class) {
                        count++;
                    }
                }
            });
           threads[i].start();

        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("count = " + count);
    }
}
