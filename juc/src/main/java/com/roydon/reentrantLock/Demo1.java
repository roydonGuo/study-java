package com.roydon.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Demo1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 * ReentrantLock为可重入锁，一个线程可多次获取锁
 **/
public class Demo1 {

    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try {
            System.out.println(" enter main...");
            m1();
        } finally {
            lock.unlock();
        }
    }

    public static void m1() {
        lock.lock();
        try {
            System.out.println(" enter m1");
            m2();
        } finally {
            lock.unlock();
        }
    }

    public static void m2() {
        lock.lock();
        try {
            System.out.println(" enter m2");
        } finally {
            lock.unlock();
        }
    }
}
