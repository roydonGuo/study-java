package com.roydon.sync;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

/**
 * java三线程交替打印0~100
 *
 * @AUTHOR: roydon
 * @DATE: 2023/8/31
 **/
public class Print3Num {

    private static final Object lock = new Object();
    private static int num = 0;
    private static final int hundred = 100;
    private static final int threadCount = 3;

    @AllArgsConstructor
    private static class Print extends Thread {
        private int threadId;

        @SneakyThrows
        @Override
        public void run() {
            while (num < hundred) {
                synchronized (lock) {
                    while (num % threadCount != threadId) {
                        lock.wait();
                    }
                    if (num <= hundred) {
                        System.out.println("Thread[" + threadId + "]: " + num);
                        num++;
                    }
                    lock.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Print(i)).start();
        }
    }

}
