package org.fwx.thread.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 分支合并框架
 * 底层使用递归方式实现
 */
public class ForkJoinDemo {
    public static void main(String[] args) {
        MyForkJoin myForkJoin = new MyForkJoin(0, 100);

        // 创建 forkjoin 连接池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(myForkJoin);

        // 获取执行结果
        try {
            Integer result = task.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // 释放连接池
        forkJoinPool.shutdown();
    }
}

class MyForkJoin extends RecursiveTask<Integer> {

    private static final int FLAG = 10;

    private Integer start;
    private Integer end;
    private Integer result = 0;

    public MyForkJoin(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        // 判断拆分任务条件
        if ((end - start) <= FLAG) {
            for (int i = start; i <= end; i++) {
                result = result + i;
            }
        } else {
            Integer middle = (start + end) / 2;
            // 左边拆分
            MyForkJoin forkJoinLeft = new MyForkJoin(start, middle);

            // 右边拆分
            MyForkJoin forkJoinRight = new MyForkJoin(middle + 1, end);

            // 执行任务拆分
            forkJoinLeft.fork();
            forkJoinRight.fork();

            // 合并结果
            result = forkJoinLeft.join() + forkJoinRight.join();
        }
        return result;
    }
}