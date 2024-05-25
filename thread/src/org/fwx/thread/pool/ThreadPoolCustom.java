package org.fwx.thread.pool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class ThreadPoolCustom {
    public static void main(String[] args) {
        /** 自定义线程池
         * corePoolSize：核心线程数
         * maximumPoolSize：最大线程数
         * keepAliveTime：空闲线程存活时间
         * unit：时间单位
         * workQueue：任务队列
         * threadFactory：线程工厂
         * handler：线程拒绝策略
         */
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
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
