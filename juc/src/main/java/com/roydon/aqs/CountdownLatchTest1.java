package com.roydon.aqs;

import java.time.LocalTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主线程等待子线程执行完成再执行
 */
public class CountdownLatchTest1 {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            service.execute(() -> {
                try {
                    System.out.println(LocalTime.now() + "子线程[" + Thread.currentThread().getName() + "]开始执行");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(LocalTime.now() + "子线程[" + Thread.currentThread().getName() + "]执行完成");
                    latch.countDown();//当前线程调用此方法，则计数减一
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        try {
            System.out.println(LocalTime.now() + "主线程[" + Thread.currentThread().getName() + "]等待子线程执行完成...");
            latch.await();//阻塞当前线程，直到计数器的值为0
            System.out.println(LocalTime.now() + "主线程[" + Thread.currentThread().getName() + "]开始执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
