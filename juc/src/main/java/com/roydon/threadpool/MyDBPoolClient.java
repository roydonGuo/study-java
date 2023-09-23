package com.roydon.threadpool;

import java.sql.Connection;

/**
 * MyDBPoolClient
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/20
 * 多线程测试数据库连接池抢占2个连接
 * 16:29:33.363 Thread-1 borrow DBConnection{name='连接2'}
 * 16:29:33.363 Thread-0 borrow DBConnection{name='连接1'}
 * 16:29:33.363 Thread-2 wait...
 * 16:29:33.363 Thread-4 wait...
 * 16:29:33.363 Thread-3 wait...
 * 16:29:33.866 Thread-0 free DBConnection{name='连接1'}
 * 16:29:33.866 Thread-3 borrow DBConnection{name='连接1'}
 * 16:29:33.866 Thread-4 borrow DBConnection{name='连接2'}
 * 16:29:33.866 Thread-2 wait...
 * 16:29:33.866 Thread-1 free DBConnection{name='连接2'}
 * 16:29:33.866 Thread-2 wait...
 * 16:29:34.374 Thread-3 free DBConnection{name='连接1'}
 * 16:29:34.374 Thread-2 borrow DBConnection{name='连接1'}
 * 16:29:34.374 Thread-4 free DBConnection{name='连接2'}
 * 16:29:34.886 Thread-2 free DBConnection{name='连接1'}
 **/
public class MyDBPoolClient {

    public static void main(String[] args) {
        MyDBPool myDBPool = new MyDBPool(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Connection borrow = myDBPool.borrow();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                myDBPool.free(borrow);
            }).start();
        }
    }

}
