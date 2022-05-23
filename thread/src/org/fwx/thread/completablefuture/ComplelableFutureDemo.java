package org.fwx.thread.completablefuture;

import java.util.concurrent.*;

/**
 * 异步调用
 */
public class ComplelableFutureDemo {
    public static void main(String[] args) throws Exception {
        ExecutorService service = Executors.newFixedThreadPool(3);

        // 1.不带返回值
        /*CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "=completableFuture1");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, service);
        completableFuture1.thenAccept((e) -> {
            System.out.println(Thread.currentThread().getName() + ":" + e);
        });*/

        // 2.带返回值
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "=completableFuture2");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // int i = 1/0;
            return 1024;
        },service);
        completableFuture2.whenComplete((v,e)->{
            // v: 返回值
            System.out.println(Thread.currentThread().getName() + ":" +v);
            // e: 异常
            System.out.println(Thread.currentThread().getName() + ":" +e);
        });

        System.out.println(Thread.currentThread().getName());

        service.shutdown();
    }
}
