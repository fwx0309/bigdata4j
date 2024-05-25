package org.fwx.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * callable 较 runnable 有返回值，可抛出异常
 * FutureTask ： 线程任务包装类，可以获取线程任务执行结果，但是在获取结果时，如果任务未完成，则阻塞等待。
 *               可以用轮询查看线程是否完成，再去获取结果。但是轮询方式，效率不高。
 */
public class D04ImplCallable {
    public static void main(String[] args) throws Exception {
        MyThread5 myThread5 = new MyThread5();

        FutureTask futureTask = new FutureTask(myThread5);

        Thread thread = new Thread(futureTask);
        thread.start();

        /**
         * 直接获取
         * 获取子线程返回结果。
         * 如果不需要返回结果：在子线程返回null；不执行futureTask.get()
         */
//        Object result = futureTask.get();
//        System.out.println("结果：" + result);

        /**
         * 轮询获取
         */
        while (true) {
            if (futureTask.isDone()) {
                Object result = futureTask.get();
                System.out.println("结果：" + result);
                break;
            }
            TimeUnit.SECONDS.sleep(1);
        }

    }
}


/**
 * MyThread5 类实现了 Callable 接口，用于在多线程环境中执行特定任务。
 * 该类的主要方法是 call，它会计算并返回一个整数的总和。
 */
class MyThread5 implements Callable {

    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            if(i%2==0){
                sum += i;
            }
        }
        return sum;
    }
}
