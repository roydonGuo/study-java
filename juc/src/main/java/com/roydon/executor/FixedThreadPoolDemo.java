package com.roydon.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FixedThreadPoolDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 * java.util.concurrent.Executors包下提供了很多创建线程池的方式，
 * 此newFixedThreadPool为固定线程数的线程池，无救急线程，阻塞队列为LinkedBlockingQueue最大容量为int的最大值
 * public static ExecutorService newFixedThreadPool(int nThreads) {
 *         return new ThreadPoolExecutor(nThreads, nThreads,
 *                                       0L, TimeUnit.MILLISECONDS,
 *                                       new LinkedBlockingQueue<Runnable>());
 *     }
 **/
public class FixedThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3); // 核心线程与最大线程数都为3，满了就进队列阻塞
        for (int i = 0; i < 5; i++) {
            executorService.submit(new MyFixedTask());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
        /**
         * pool-1-thread-1
         * pool-1-thread-2
         * pool-1-thread-3
         * pool-1-thread-1
         * pool-1-thread-2
         */
    }

    static class MyFixedTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
