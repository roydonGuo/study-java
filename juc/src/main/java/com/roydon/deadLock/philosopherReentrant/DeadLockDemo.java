package com.roydon.deadLock.philosopherReentrant;

/**
 * DeadLockDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 * ReentrantLock 解决哲学家就餐问题
 **/
public class DeadLockDemo {
    public static void main(String[] args) {
        Chopstick chopstick1 = new Chopstick("1");
        Chopstick chopstick2 = new Chopstick("2");
        Chopstick chopstick3 = new Chopstick("3");
        Chopstick chopstick4 = new Chopstick("4");
        Chopstick chopstick5 = new Chopstick("5");
        new Philosopher("p1",chopstick1,chopstick2).start();
        new Philosopher("p2",chopstick2,chopstick3).start();
        new Philosopher("p3",chopstick3,chopstick4).start();
        new Philosopher("p4",chopstick4,chopstick5).start();
        new Philosopher("p5",chopstick5,chopstick1).start();
    }
}
