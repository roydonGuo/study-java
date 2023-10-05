package com.roydon.executor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 * java.util.concurrent.Executors包下提供的创建线程池的方式
 * 延迟任务
 **/
public class ScheduledThreadPoolDemo {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);

        System.out.println(LocalTime.now());

        executorService.schedule(new MyScheduledTask(),0, TimeUnit.SECONDS);
        executorService.schedule(new MyScheduledTask(),1, TimeUnit.SECONDS);
        executorService.schedule(new MyScheduledTask(),5, TimeUnit.SECONDS);

        executorService.shutdown();

    }

    static class MyScheduledTask implements Runnable {

        @Override
        public void run() {
            try {
                String name = Thread.currentThread().getName();
                System.out.println(name + ", start ." + LocalTime.now());
                Thread.sleep(2000);
                System.out.println(name + ", finish ." + LocalTime.now());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
