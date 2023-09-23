package com.roydon.reentrantLock;

import java.time.LocalTime;
import java.util.concurrent.locks.ReentrantLock;

/**
 * InterruptiblyDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 * 可打断加锁避免死锁，若无竞争此方法会获取锁，反之有竞争就会进入阻塞队列，可以被其他线程用interrupted()打断
 **/
public class InterruptiblyDemo {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println(LocalTime.now() + "尝试获取锁...");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println(LocalTime.now() + "获取锁失败");
                return;
            }
            try {
                System.out.println(LocalTime.now() + "获取锁成功");
            } finally {
                lock.unlock();
            }
        });

        // 启动之前，主线程已经获取到锁，再启动t1，t1就会进入阻塞
        lock.lock();
        t1.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        t1.interrupt();
        lock.unlock();

    }
}
