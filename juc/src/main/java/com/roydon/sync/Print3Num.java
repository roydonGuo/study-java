package com.roydon.sync;

/**
 * Print3Num
 *
 * @AUTHOR: roydon
 * @DATE: 2023/8/31
 * java三线程交替打印0~100
 **/
public class Print3Num {

    private static final Object lock = new Object();
    private static int num = 0;
    private static final int hundred = 100;
    private static final int threadCount = 3;

    private static class Print extends Thread {
        private int threadId;

        public Print(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            while (num < hundred) {
                synchronized (lock) {
                    while (num % threadCount != threadId) {
                        try {
                            lock.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
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
