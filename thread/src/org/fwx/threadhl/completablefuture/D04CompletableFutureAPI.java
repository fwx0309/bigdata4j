package org.fwx.threadhl.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @auther zzyy
 * @create 2022-01-17 15:20
 */
public class D04CompletableFutureAPI
{
    public static void main(String[] args) throws Exception
    {
        group1();
    }

    /**
     * 获得结果和触发计算的几种方式
     * @throws InterruptedException
     * @throws ExecutionException
     */
    private static void group1() throws Exception
    {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        // 等待CompletableFuture完成并获取其结果。如果CompletableFuture已经完成，则立即返回结果。
        // 如果未完成，则会阻塞当前线程直到完成，或抛出ExecutionException或InterruptedException异常。
        System.out.println(completableFuture.get());

        // 等待CompletableFuture完成并获取其结果，但最多只等待指定的时长。如果在指定时间内完成，则返回结果。
        // 如果超时未完成，则抛出TimeoutException。这是一个处理异步结果时非常有用的工具，可以避免无限等待。
        System.out.println(completableFuture.get(2L,TimeUnit.SECONDS));

        // 等待CompletableFuture完成，与get()类似，但不会抛出ExecutionException。join()主要用于非受阻塞的线程中。
        // 它使当前线程等待异步计算完成，然后可以继续处理结果，而不需要处理异常。
        System.out.println(completableFuture.join());


        //暂停几秒钟线程
        //try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        // 输出CompletableFuture中已经存在的值，如果不存在则返回默认值"xxx"
        System.out.println(completableFuture.getNow("xxx"));

        // 为CompletableFuture设置完成值"completeValue"，并打印该值及其获取过程的状态
        System.out.println(completableFuture.complete("completeValue")+"\t"+completableFuture.get());

    }
}
