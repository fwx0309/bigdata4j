package org.fwx.thread.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号灯
 * 申请固定的信号灯数，信号灯没有释放时，其它的线程无法进入执行队列。类似于停车位示例。
 *
 * SemaPhoreDemo 类展示了如何使用 Semaphore 来控制同时访问某个资源的线程数量。
 * 在这个示例中，我们创建了一个 Semaphore 对象，初始化为 3，意味着最多只有 3 个线程可以同时访问资源。
 * 当一个线程获得了 Semaphore 的许可时，它就可以访问资源，并在完成之后释放这个许可。
 * 如果所有许可都被占用，其他试图获得许可的线程将会被阻塞，直到有许可被释放。
 */
public class SemaPhoreDemo {
    public static void main(String[] args) {
        // 创建一个 Semaphore，最多允许 3 个线程同时访问
        Semaphore semaphore = new Semaphore(3);

        // 循环创建 6 个线程，每个线程会尝试获取 Semaphore 的许可，然后模拟访问资源
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        // 尝试获取 Semaphore 的许可，如果所有许可都被占用，线程将被阻塞
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName() + ": in"); // 线程获得许可，打印进入信息

                        // 模拟线程执行任务，随机睡眠 0 到 3 秒
                        TimeUnit.SECONDS.sleep(new Random().nextInt(3));

                        System.out.println(Thread.currentThread().getName() + ": out"); // 执行完毕，打印离开信息

                    } catch (InterruptedException e) {
                        e.printStackTrace(); // 线程被中断时的处理
                    } finally {
                        // 无论线程是正常结束还是被中断，最后都要释放许可
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start(); // 启动线程，线程名设置为数字 i
        }
    }
}

