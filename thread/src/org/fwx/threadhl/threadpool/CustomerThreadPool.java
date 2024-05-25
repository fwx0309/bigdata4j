package org.fwx.threadhl.threadpool;

import java.util.concurrent.*;

/**
 * @ClassName CustomerThreadPool
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/25 15:16
 * @Version 1.0
 */
public class CustomerThreadPool {

    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        int cpus = runtime.availableProcessors();
        System.out.println("当前计算机的CPU核数：" + cpus);

        // 创建线程池执行器
        // 参数详细说明：
        // 1. 核心线程数：线程池始终保留的最小线程数。
        // 2. 最大线程数：线程池可容纳的最大线程数。
        // 3. 线程保持时间：如果线程池中的线程超过核心线程数，并且空闲时间超过此参数，线程将被终止并从池中移除。
        // 4. 时间单位：上一参数的时间单位。
        // 5. 工作队列：线程池使用的队列，用于存储等待执行的任务。此处使用ArrayBlockingQueue，容量为100。
        // 6. 线程工厂：用于创建新线程的工厂，使用JDK提供的默认线程工厂。
        // 7. 拒绝策略：当线程池无法执行任务时的处理策略，此处使用AbortPolicy，即抛出RejectedExecutionException异常。
        ThreadPoolExecutor executor = new ThreadPoolExecutor(4,
                10,
                10L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            // 1.提交 Runnable 任务
            /*for (int i = 0; i < 100; i++) {
                executor.execute(() -> System.out.println(Thread.currentThread().getName() + "正在执行"));
            }*/

            // 2.提交 Callable 任务, 并获取执行结果
            /*for (int i = 0; i < 100; i++) {
                Future<String> future = executor.submit(() -> {
                    return Thread.currentThread().getName() + "正在执行";
                });
                // 获取任务执行结果，会阻塞当前线程，直到任务执行完毕。
                System.out.println("future = " + future.get());
            }*/

            // 3.提交 Callable 任务，不获取执行结果
            for (int i = 0; i < 100; i++) {
                executor.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "正在执行");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executor.shutdown();
        }
    }
}
