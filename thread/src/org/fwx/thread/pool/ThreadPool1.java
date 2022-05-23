package org.fwx.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPool1 {
    public static void main(String[] args) {
        Print1 print1 = new Print1();

        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 10; i++) {
            service.submit(print1);
        }

        service.shutdown();

    }
}

class Print1 implements Runnable{

    private AtomicInteger num = new AtomicInteger(1);

    @Override
    public void run() {
        while (num.get() <= 100){
            System.out.println(Thread.currentThread().getName() + ":" + num.getAndIncrement());
        }
    }
}
