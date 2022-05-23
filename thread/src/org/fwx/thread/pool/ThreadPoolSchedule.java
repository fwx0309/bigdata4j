package org.fwx.thread.pool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 可调度的线程池
 */
public class ThreadPoolSchedule {
    public static void main(String[] args) {
        Print2 print2 = new Print2();

        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 10; i++) {
            ScheduledFuture future = service.schedule(print2, 1, TimeUnit.SECONDS);

            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        service.shutdown();
    }
}

class Print2 implements Callable {
    @Override
    public Object call() throws Exception {
        Random num = new Random();
        return Thread.currentThread().getName() + ":" +num.nextInt(100);
    }
}
