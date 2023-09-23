package com.roydon.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * ExecutorServiceDemo1
 *
 * @AUTHOR: roydon
 * @DATE: 2023/9/7
 **/
public class ExecutorServiceDemo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
                System.out.println("runnable is over!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "callable task is over!";
        };

        ArrayList<Callable<String>> callableList = new ArrayList<>();
        callableList.add(callable);
        callableList.add(callable);
        callableList.add(callable);

        // 1、execute
//        executorService.execute(runnable);
        // 2、submit
//        Future<String> future = executorService.submit(callable);
        // 2.1、阻塞方法 get(),正在被执行的任务随时都可能抛出异常或中断执行,故此方法需要放在 try catch 语句块中
//        String result = null;
//        try {
//            result = future.get();
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
        // 2.2、因为get() 方法是阻塞的，而且并不知道要阻塞多长时间。因此可能导致应用程序的性能降低。如果结果数据并不重要，那么我们可以使用超时机制来避免长时间阻塞。
//        String result = future.get(200, TimeUnit.MILLISECONDS);
        // 3、invokeAny():返回任意一个成功执行的任务的结果
//        String s = executorService.invokeAny(callableList);
//        System.out.println(s);
        // 4、invokeAll():返回所有任务执行的结果
        List<Future<String>> futures = executorService.invokeAll(callableList);

        futures.forEach(f -> {
            try {
                System.out.println(f.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

//        executorService.shutdown();// 停止接受任务，所有正在运行的线程完成当前工作后关闭。
//        List<Runnable> runnables = executorService.shutdownNow();// 立即销毁 ExecutorService 实例,返回等待处理的任务列表
        // 两方法相结合并结合 awaitTermination() 方法。使用这种方式，ExecutorService 首先停止执行新任务，等待指定的时间段完成所有任务。如果该时间到期，则立即停止执行。
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }

        // 延时任务
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 1、延时 1 秒执行任务 callable
//        ScheduledFuture<String> schedule = scheduledExecutorService.schedule(callable, 1, TimeUnit.SECONDS);
//        System.out.println(schedule.get());
        // 2、在固定延迟后定期执行任务:下方示例在 1秒的初始延迟后执行任务，之后，它将每秒执行相同的任务。(前提是线程池不关闭，才能持续执行)
//        ScheduledFuture<?> scheduleAtFixedRate = scheduledExecutorService.scheduleAtFixedRate(runnable, 1, 1, TimeUnit.SECONDS);
        // 3、当前执行结束与另一个执行结束之间的暂停时间为 1 秒
        ScheduledFuture<?> scheduleWithFixedDelay = scheduledExecutorService.scheduleWithFixedDelay(runnable, 1, 1, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();

        String roydon = new String("roydon");
        String roydon2 = new String("roydon");

        System.out.println(roydon == roydon2);

    }

}
