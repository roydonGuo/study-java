package com.roydon.reentrantLock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLockDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/8/31
 * juc读写互斥的读写锁
 * 读写锁允许多个线程同时读取共享资源，但只允许一个线程进行写操作，以提高并发性和性能。
 * 在代码中，我们创建了一个Main类和两个内部类Reader和Writer，分别用于实现读操作和写操作。
 * <p>
 * 在Main类中，我们定义了一个ReentrantReadWriteLock对象lock和一个共享的数据变量sharedData。在Reader的run方法中，通过lock.readLock().lock()获取读锁，然后读取共享数据，并在控制台输出读取的结果。最后，通过lock.readLock().unlock()释放读锁。
 * <p>
 * 在Writer的run方法中，通过lock.writeLock().lock()获取写锁，然后对共享数据进行写操作（在示例中是将sharedData加一），并在控制台输出写操作的结果。最后，通过lock.writeLock().unlock()释放写锁。
 * <p>
 * 通过使用读写锁，多个线程可以同时读取共享数据，而只有一个线程可以进行写操作。这样可以提高读操作的并发性能，同时保证写操作的原子性和一致性。
 * <p>
 * 请注意，读写锁的使用需要根据具体的场景和需求进行合理的设计和调整。
 **/
public class ReadWriteLockDemo {
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static int sharedData = 0;

    private static class Reader extends Thread {
        @Override
        public void run() {
            lock.readLock().lock();
            try {
                System.out.println("Reader " + Thread.currentThread().getId() + " reads: " + sharedData);
            } finally {
                lock.readLock().unlock();
            }
        }
    }

    private static class Writer extends Thread {
        @Override
        public void run() {
            lock.writeLock().lock();
            try {
                sharedData++;
                System.out.println("Writer writes: " + sharedData);
            } finally {
                lock.writeLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Reader()).start();

        new Thread(new Writer()).start();
        new Thread(new Writer()).start();

        new Thread(new Reader()).start();
//        new Thread(new Reader()).start();
    }

}
