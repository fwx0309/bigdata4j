package org.fwx.thread.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 减少计数
 * 闭锁：等待多个分线程执行全部执行完，再执行主流程。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        CDLatch cdLatch = new CDLatch(countDownLatch);

        Long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(cdLatch).start();
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("耗时：" + (endTime - startTime));
    }
}

class CDLatch implements Runnable {

    private CountDownLatch countDownLatch;

    public CDLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }

        countDownLatch.countDown();
    }
}
