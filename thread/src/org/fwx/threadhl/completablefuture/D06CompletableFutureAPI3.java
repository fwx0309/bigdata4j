package org.fwx.threadhl.completablefuture;

import java.util.concurrent.CompletableFuture;

/**
 * @auther zzyy
 * @create 2022-01-17 17:18
 */
public class D06CompletableFutureAPI3
{
    public static void main(String[] args)
    {
        /*CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f ->{
            return f + 2;
        }).thenApply(f ->{
            return f + 3;
        }).thenAccept(System.out::println);*/

        // 示例1：使用CompletableFuture创建一个异步任务，该任务返回"resultA"，然后立即执行一个空的Runnable，最后通过join方法等待任务完成。
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenRun(() -> {}).join());

        // 示例2：与示例1相似，但此处使用thenAccept方法来消费结果"resultA"，并打印出来，而不是进行后续的计算或操作。
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenAccept(r -> System.out.println(r)).join());

        // 示例3：创建一个异步任务返回"resultA"，然后使用thenApply方法对结果进行变换，将其加上"resultB"，最后通过join方法等待任务完成并打印结果。
        System.out.println(CompletableFuture.supplyAsync(() -> "resultA").thenApply(r -> r + "resultB").join());


    }
}
