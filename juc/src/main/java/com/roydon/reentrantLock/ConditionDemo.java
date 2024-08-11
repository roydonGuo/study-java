package com.roydon.reentrantLock;

import java.time.LocalTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ConditionDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 * ReentrantLock条件变量，可有多个阻塞队列，指定阻塞队列notify
 **/
public class ConditionDemo {

    private static boolean beginGenshin = false;
    private static boolean beginHonkai = false;

    final static ReentrantLock ROOM = new ReentrantLock();
    static Condition waitGenshinSet = ROOM.newCondition();
    static Condition waitHonkaiSet = ROOM.newCondition();

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            ROOM.lock();
            try {
                System.out.println(LocalTime.now() + "原神，");
                while (!beginGenshin) {
                    System.out.println(LocalTime.now() + "不启动？");
                    try {
                        waitGenshinSet.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(LocalTime.now() + "启动！");
            } finally {
                ROOM.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            ROOM.lock();
            try {
                System.out.println(LocalTime.now() + "星铁，");
//                while (!beginHonkai) {
                    System.out.println(LocalTime.now() + "不启动？");
                    try {
                        waitHonkaiSet.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
//                }
                System.out.println(LocalTime.now() + "启动！");
            } finally {
                ROOM.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();

        Thread.sleep(1000);

        new Thread(() -> {
            ROOM.lock();
            try {
                beginGenshin = true;
                waitGenshinSet.signal(); // notify waitGenshinSet
            } finally {
                ROOM.unlock();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            ROOM.lock();
            try {
                beginHonkai = true;
                waitHonkaiSet.signal(); // notify waitHonkaiSet
            } finally {
                ROOM.unlock();
            }
        }).start();

    }
}
