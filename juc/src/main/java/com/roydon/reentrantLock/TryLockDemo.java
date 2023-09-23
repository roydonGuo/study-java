package com.roydon.reentrantLock;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * TryLockDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 * 立即打断获取锁
 **/
public class TryLockDemo {

    private final static ReentrantLock LOCK = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        System.out.println(LocalTime.now() + "原神，");

        Thread t1 = new Thread(() -> {
            try {
                if (!LOCK.tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(LocalTime.now() + "未获取到锁");
                } else {
                    System.out.println(LocalTime.now() + "启动！");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                LOCK.unlock();
            }
        }, "t1");

        LOCK.lock();
        System.out.println(LocalTime.now() + "主线程上锁");
        t1.start();
        Thread.sleep(500);
        LOCK.unlock();

    }
}
