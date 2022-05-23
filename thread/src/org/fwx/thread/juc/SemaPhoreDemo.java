package org.fwx.thread.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 *
 * 申请固定的信号灯数，信号灯没有释放时，其它的线程无法进入执行队列。类似于停车位示例。
 */
public class SemaPhoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + ": in");

                        TimeUnit.SECONDS.sleep(new Random().nextInt(3));

                        System.out.println(Thread.currentThread().getName() + ": out");

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
