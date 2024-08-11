package com.roydon.thread;

/**
 * ThreadLocalDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 **/
public class ThreadLocalDemo {
    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            threadLocal.set("thread1");
            print();
            System.out.println("t1 after get: " + threadLocal.get());
        }).start();
        new Thread(() -> {
            threadLocal.set("thread2");
            print();
            System.out.println("t2 after get: " + threadLocal.get());
        }).start();
    }

    private static void print() {
        System.out.println(threadLocal.get());
        threadLocal.remove();
    }
}
