package org.fwx.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
        MyCallable callable = new MyCallable();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callable);

        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            Integer sum = futureTask.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}

class MyCallable implements Callable{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        return sum;
    }
}
