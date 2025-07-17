package org.fwx.threadhl.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName D10CompletableAllof
 * @Description 调用 allOf 方法
 *              ps: 只要用join方法，则主线程会阻塞，直到所有异步任务执行完毕
 * @Author Fwx
 * @Date 2024/6/23 8:53
 * @Version 1.0
 */
public class D10CompletableAllof {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println("future...");
            return 10;
        },pool);
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
            System.out.println("future1...");
            return 20;
        },pool);

        // 会阻塞
        /*CompletableFuture.allOf(future, future1)
                .thenRun(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(future.join() + future1.join());})
                .join();*/

        // 会阻塞
//        future.join();
//        future1.join();

        System.out.println("main...");

        pool.shutdown();
    }
}
