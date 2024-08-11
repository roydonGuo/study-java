package com.roydon.executor;

import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

/**
 * ThreadPoolExecutorDemo
 *
 * @AUTHOR: roydon
 * @DATE: 2023/10/3
 * 线程池核心参数讲解
 **/
public class ThreadPoolExecutorDemo {

    public static void main(String[] args) {
        /**
         * io密集型场景，定义线程数为 cpu线程数*2+1
         * cpu密集型，定义线程数为 cpu核数+1
         */
        System.out.println("cpu核心数：" + Runtime.getRuntime().availableProcessors());
        AtomicInteger integer = new AtomicInteger(1);
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(2);
//        LinkedBlockingQueue<Object> queue = new LinkedBlockingQueue<>(2);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2,
                3,
                0,
                TimeUnit.MILLISECONDS,
                queue,
                f -> new Thread(f, "myThread" + integer.getAndIncrement()),
                new ThreadPoolExecutor.DiscardPolicy()
        );
        showStatus(queue, executor);
        executor.submit(new MyTask("1", 3600000));
        showStatus(queue, executor);
        executor.submit(new MyTask("2", 3600000));
        showStatus(queue, executor);    //    pool size: 2, queue: [] ，两个核心线程执行两个长任务，阻塞队列为空

        executor.submit(new MyTask("3"));
        showStatus(queue, executor);             // pool size: 2, queue: [MyTask[3]]
        executor.submit(new MyTask("4"));
        showStatus(queue, executor);        //  pool size: 2, queue: [MyTask[3], MyTask[4]]

        /**
         * running...>>> myTask 5
         * running...>>> myTask 3
         * running...>>> myTask 4
         * pool size: 2, queue: []
         */
//        executor.submit(new MyTask("5"));
        executor.submit(new MyTask("5", 10000));
        showStatus(queue, executor);

        executor.submit(new MyTask("6"));
        showStatus(queue, executor);     // 若5为长任务交由紧急线程处理，这是6又来了会直接触发拒绝策略
    }


    private static void showStatus(ArrayBlockingQueue<Runnable> queue, ThreadPoolExecutor executor) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<Object> tasks = new ArrayList<>();
        for (Runnable runnable : queue) {
            try {
                Field callable = FutureTask.class.getDeclaredField("callable");
                callable.setAccessible(true);
                Object adapter = callable.get(runnable);
                Class<?> clazz = Class.forName("java.util.concurrent.Executors$RunnableAdapter");
                Field task = clazz.getDeclaredField("task");
                task.setAccessible(true);
                Object a = task.get(adapter);
                tasks.add(a);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("pool size: " + executor.getPoolSize() + ", queue: " + tasks);
    }

    static class MyTask implements Runnable {
        private String name;
        private long duration;

        public MyTask(String name) {
            this(name, 0);
        }

        public MyTask(String name, long duration) {
            this.name = name;
            this.duration = duration;
        }

        @Override
        public void run() {
            System.out.println("running...>>> myTask " + name);
            try {
                Thread.sleep(duration);
                System.out.println("finish...>>> myTask " + name);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public String toString() {
            return "MyTask[" + name + "]";
        }
    }


}
