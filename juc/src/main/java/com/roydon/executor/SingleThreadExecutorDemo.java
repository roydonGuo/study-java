package com.roydon.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadExecutorDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 * java.util.concurrent.Executors包下提供的创建线程池的方式，
 * 核心线程数与最大线程数都为1，可以保证任务顺序执行
 * public static ExecutorService newSingleThreadExecutor() {
 *      return new FinalizableDelegatedExecutorService
 *      (new ThreadPoolExecutor(1, 1,
 *          0L, TimeUnit.MILLISECONDS,
 *          new LinkedBlockingQueue<Runnable>()));
 * }
 **/
public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 100; i++) {
            executorService.submit(new MySingleTask());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
    }

    static int count = 0;

    static class MySingleTask implements Runnable {
        @Override
        public void run() {
            count++;
            System.out.println(Thread.currentThread().getName() + " : " + count);
        }
    }
}
