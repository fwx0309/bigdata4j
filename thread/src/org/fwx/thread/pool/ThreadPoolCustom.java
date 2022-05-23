package org.fwx.thread.pool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class ThreadPoolCustom {
    public static void main(String[] args) {
        // 自定义线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                10,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < 20; i++) {
            int num = i;
            threadPool.submit(()->{
                System.out.println(Thread.currentThread().getName() + ":" + num);
            });
        }

        // 释放资源
        threadPool.shutdown();
    }
}
