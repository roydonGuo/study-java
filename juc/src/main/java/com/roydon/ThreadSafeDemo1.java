package com.roydon;

import java.util.ArrayList;
import java.util.List;

/**
 * ThreadSafeDemo1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/7
 * 如何保证线程安全
 **/
public class ThreadSafeDemo1 {
    public static final int LOOP_COUNT = 200;
    public static final int THREAD_COUNT = 2;

    public static void main(String[] args) {
        ThreadSafe threadSafe = new ThreadSafe();
        for (int i = 0; i < THREAD_COUNT; i++) {
            new Thread(() -> {
                threadSafe.method1(LOOP_COUNT);
            }, "thread-" + i).start();
        }
    }

}

class ThreadUnSafe {

    // 共享变量不具有原子性
    List<String> arrayList = new ArrayList<>();

    public void method1(int loopCount) {
        for (int i = 0; i < loopCount; i++) {
            method2();
            method3();
        }
    }

    public void method2() {
        arrayList.add("roydon");
    }

    public void method3() {
        arrayList.remove(0);
    }

}

class ThreadSafe {

    public final void method1(int loopCount) {
        List<String> arrayList = new ArrayList<>();
        for (int i = 0; i < loopCount; i++) {
            method2(arrayList);
            method3(arrayList);
        }
    }

    private void method2(List<String> arrayList) {
        arrayList.add("roydon");
    }

    private void method3(List<String> arrayList) {
        // safe
        arrayList.remove(0);
        // unsafe
//        new Thread(() -> {
//            arrayList.remove(0);
//        }).start();
    }

}

