package com.roydon.deadLock.philosopherReentrant;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Philosopher
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 **/
public class Philosopher extends Thread {

    private String name;

    Chopstick left;
    Chopstick right;

    public Philosopher(String name, Chopstick left, Chopstick right) {
        this.name = name;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        while (true) {
            // 获取到左右筷子才能吃饭
            if (left.tryLock()) {
                try {
                    if (right.tryLock()) {
                        try {
                            eat();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } finally {
                            right.unlock();
                        }
                    }
                } finally {
                    left.unlock();
                }
            }
        }
    }

    private void eat() throws InterruptedException {
        System.out.println(name + " use " + left.getName() + " " + right.getName() + " 开始吃饭。。。");
        Thread.sleep(1000);
    }
}
