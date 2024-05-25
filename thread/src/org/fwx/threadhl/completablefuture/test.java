package org.fwx.threadhl.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName test
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/24 14:19
 * @Version 1.0
 */
public class test {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);

        AtomicInteger num = new AtomicInteger();

        try {
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
                for (int i = 0; i < 5; i++) {
                    try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {throw new RuntimeException(e);}
                    System.out.println("i = " + i);
                }
                num.set(10);
                return "hello";
            }, service).whenComplete((t, u) -> {
                if (u == null) {
                    System.out.println(Thread.currentThread().getName() + ":" + t);
                    System.out.println("num = " + num.get());
                }
            });

            System.out.println("main...退出");

        } finally {
            service.shutdown();
        }


    }
}
