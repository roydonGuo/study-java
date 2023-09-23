package com.roydon.deadLock;

/**
 * Java 死锁产生的四个必要条件：
 * <p>
 * 1. 互斥条件，即当资源被一个线程使用（占有）时，别的线程不能使用
 * 2. 不可剥夺条件，资源请求者不能强制从资源占有者手中夺取资源，资源只能由资源占有者主动释放
 * 3. 请求和保持条件，即当资源请求者在请求其他的资源的同时保持对原有资源的占有
 * 4. 循环等待条件，即存在一个等待循环队列：p1 要 p2 的资源，p2 要 p1 的资源，形成了一个等待环路
 * <p>
 * 四个条件都成立的时候，便形成死锁。死锁情况下打破上述任何一个条件，便可让死锁消失
 */
public class Dead {
    public static final Object resources1 = new Object();
    public static final Object resources2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            // 线程1：占用资源1 ，请求资源2
            synchronized (resources1) {
                System.out.println("线程1已经占用了资源1，开始请求资源2");
                try {
                    Thread.sleep(2000);//休息两秒，防止线程1直接运行完成。
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                //2秒内线程2肯定可以锁住资源2
                synchronized (resources2) {
                    System.out.println("线程1已经占用了资源2");
                }
            }
        }).start();
        new Thread(() -> {
            // 线程2：占用资源2 ，请求资源1
            synchronized (resources2) {
                System.out.println("线程2已经占用了资源2，开始请求资源1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (resources1) {
                    System.out.println("线程2已经占用了资源1");
                }
            }
        }).start();
    }
}
