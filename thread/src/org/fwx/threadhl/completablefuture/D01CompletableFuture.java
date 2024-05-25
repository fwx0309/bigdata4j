package org.fwx.threadhl.completablefuture;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName D01CompletableFuture
 * @Description CompletableFuture介绍
 *  1. CompletableFuture 是 Java8 中提供的一个类，它表示异步计算结果。
 *  2. CompletableFuture 是一个异步编程的类，它提供了异步操作的回调，并且提供了异步操作的组合。
 *
 * @Author Fwx
 * @Date 2024/5/22 8:09
 * @Version 1.0
 */
public class D01CompletableFuture {

    /**
     * 1. 无返回值
     * 2. 异步执行
     * 3. 线程池默认使用 ForkJoinPool.commonPool()
     * 4. 默认使用默认线程池
     */
    @Test
    public void test() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("没有返回结果...");
        });

        System.out.println(completableFuture.get());
    }

    /**
     * 1. 无返回值
     * 2. 异步执行
     * 3. 使用声明的线程池
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("没有返回结果...");
            }, threadPool);

        System.out.println(completableFuture.get());
    }

    /**
     * 1. 有返回值
     * 2. 异步执行
     * 3. 线程池默认使用 ForkJoinPool.commonPool()
     * 4. 默认使用默认线程池
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回结果...");
            return 1;
        });

        System.out.println(completableFuture.get());
    }

    /**
     * 1. 有返回值
     * 2. 异步执行
     * 3. 使用声明的线程池
     */
    @Test
    public void test4() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("有返回结果...");
            return 1;
        },threadPool);

        System.out.println(completableFuture.get());
    }

}
