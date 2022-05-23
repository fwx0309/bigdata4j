package org.fwx.thread.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 将100个数字通个不同的线程来输出
 */
public class ThreadPool {
    public static void main(String[] args) {

        ArrayList<Integer> dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add(i);
        }

        // 数据条数
        int dataSize = dataList.size();
        // 每个线程处理数据条数
        int threadDataSize = 10;
        // 计算线程数
        int threadSize = 0;
        if (dataSize % threadDataSize == 0){
            threadSize = dataSize / threadDataSize;
        } else {
            threadSize = dataSize / threadDataSize + 1;
        }

        // 创建线程池
        ExecutorService service = Executors.newFixedThreadPool(threadSize);

        // 创建任务集合
        ArrayList<Callable<Integer>> tasks = new ArrayList<>();
        Callable<Integer> task = null;

        for (int i = 0; i < threadSize; i++) {
            List<Integer> subList;

            if (i == threadSize -1) {
                subList = dataList.subList(i * threadDataSize,dataSize);
            } else {
                subList = dataList.subList(i * threadDataSize, (i + 1) * threadDataSize);
            }

            // 匿名 Callable
            task = new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int sum = 0;
                    for (Integer data : subList) {
                        System.out.println(Thread.currentThread().getName() + ":" + data);
                        sum += data;
                    }
                    return sum;
                }
            };
            // 将数据添加至任务集合
            tasks.add(task);
        }


        try {
            List<Future<Integer>> futures = service.invokeAll(tasks);
            for (Future<Integer> future : futures) {
                System.out.println("结果：" + future.get());
            }
            service.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}