package com.roydon.waitnotify;

import java.time.LocalTime;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * WaitNotifyABC
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/9
 **/
public class WaitNotifyPrintABC {

    static Thread t1, t2, t3;

    public static void main(String[] args) throws InterruptedException {
//        WaitNotify waitNotify = new WaitNotify(1, 3);
//
//        new Thread(() -> {
//            waitNotify.print("A", 1, 2);
//        }).start();
//        new Thread(() -> {
//            waitNotify.print("B", 2, 3);
//        }).start();
//        new Thread(() -> {
//            waitNotify.print("C", 3, 1);
//        }).start();

        // =================================

//        AwaitSignal awaitSignal = new AwaitSignal(3);
//        Condition a = awaitSignal.newCondition();
//        Condition b = awaitSignal.newCondition();
//        Condition c = awaitSignal.newCondition();
//
//        new Thread(() -> {
//            awaitSignal.print("a", a, b);
//        }).start();
//        new Thread(() -> {
//            awaitSignal.print("b", b, c);
//        }).start();
//        new Thread(() -> {
//            awaitSignal.print("c", c, a);
//        }).start();
//
//        Thread.sleep(1000);
//
//        awaitSignal.lock();
//        try {
//            a.signal();
//        } finally {
//            awaitSignal.unlock();
//        }

        // ====================================

        ParkUnpark parkUnpark = new ParkUnpark(3);
        t1 = new Thread(() -> {
            parkUnpark.print("a", t2);
        });
        t2 = new Thread(() -> {
            parkUnpark.print("b", t3);
        });
        t3 = new Thread(() -> {
            parkUnpark.print("c", t1);
        });

        t1.start();
        t2.start();
        t3.start();

        LockSupport.unpark(t1);

    }
}

/**
 * 顺序打印 abc
 * a --- 1 -- 2
 * b --- 2 -- 3
 * c --- 3 -- 1
 */
class WaitNotify {
    private int flag;
    private int loopCount;

    public WaitNotify(int flag, int loopCount) {
        this.flag = flag;
        this.loopCount = loopCount;
    }

    public synchronized void print(String name, int waitFlag, int nextFlag) {
        for (int i = 0; i < loopCount; i++) {
            while (flag != waitFlag) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(LocalTime.now() + "-----" + name);
            flag = nextFlag;
            this.notifyAll();
        }
    }
}

class ParkUnpark {
    private int loopCount;

    public ParkUnpark(int loopCount) {
        this.loopCount = loopCount;
    }

    public void print(String name, Thread next) {
        for (int i = 0; i < loopCount; i++) {
            LockSupport.park();
            System.out.println(LocalTime.now() + " -- " + name);
            LockSupport.unpark(next);
        }
    }
}

class AwaitSignal extends ReentrantLock {
    private int loopCount;

    public AwaitSignal(int loopCount) {
        this.loopCount = loopCount;
    }

    public void print(String name, Condition cur, Condition next) {
        for (int i = 0; i < loopCount; i++) {
            lock();
            try {
                cur.await();
                System.out.println(LocalTime.now() + " --- " + name);
                next.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                unlock();
            }
        }
    }

}
