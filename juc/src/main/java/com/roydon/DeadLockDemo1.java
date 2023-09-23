package com.roydon;

/**
 * DeadLockDemo1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/7
 * 死锁演示
 **/
public class DeadLockDemo1 {

    public static void main(String[] args) {
        StringBuffer buffer1 = new StringBuffer();
        StringBuffer buffer2 = new StringBuffer();

        new Thread(() -> {
            synchronized (buffer1) {
                buffer1.append("a");
                buffer2.append("1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (buffer2) {
                    buffer1.append("b");
                    buffer2.append("2");
                    System.out.println("buffer1 = " + buffer1);
                    System.out.println("buffer2 = " + buffer2);
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (buffer2) {
                buffer1.append("c");
                buffer2.append("3");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (buffer1) {
                    buffer1.append("d");
                    buffer2.append("4");
                    System.out.println("buffer1 = " + buffer1);
                    System.out.println("buffer2 = " + buffer2);
                }
            }
        }).start();

    }

}
