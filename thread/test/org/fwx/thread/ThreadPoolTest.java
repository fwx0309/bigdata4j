package org.fwx.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(4);

        // 可以将工具类创建出来的线程池强转后修改其参数
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        service1.setCorePoolSize(2);
        service1.setMaximumPoolSize(4);
        service1.setKeepAliveTime(3, TimeUnit.SECONDS);

        System.out.println(service1.getCorePoolSize());
        System.out.println(service1.getPoolSize());
        System.out.println(service1.getMaximumPoolSize());
        System.out.println(service1.getKeepAliveTime(TimeUnit.SECONDS));

        service.execute(new MyPoolRunnable());

        service.shutdown();
    }
}

class MyPoolRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}
