package org.fwx.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable 较 runnable 有返回值，可抛出异常
 */
public class ImplCallable {
    public static void main(String[] args) {
        MyThread5 myThread5 = new MyThread5();

        FutureTask futureTask = new FutureTask(myThread5);

        Thread thread = new Thread(futureTask);
        thread.start();

        // 获取子线程返回结果。
        // 如果不需要返回结果：
        //      在子线程返回null；
        //      不执行futureTask.get()
        try {
            Object result = futureTask.get();
            System.out.println("结果：" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

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
