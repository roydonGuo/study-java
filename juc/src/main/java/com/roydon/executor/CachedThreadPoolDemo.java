package com.roydon.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CachedThreadPoolDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 * java.util.concurrent.Executors包下提供的创建线程池的方式
 * 可缓存线程池
 * 阻塞队列SynchronousQueue不存储元素，每个插入操作都必须等待一个移除操作
 * 有存活的临时线程就用存活的，否则创建线程执行任务
 * 适用于任务多，任务执行短的场景
 * public static ExecutorService newCachedThreadPool() {
 * return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
 * 60L, TimeUnit.SECONDS,
 * new SynchronousQueue<Runnable>());
 * }
 **/
public class CachedThreadPoolDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            executorService.submit(new MyCachedTask());
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        executorService.shutdown();
    }

    static class MyCachedTask implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName() + " finish .");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
