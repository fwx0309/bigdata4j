package org.fwx.threadhl.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目：实现一个自旋锁,复习CAS思想
 * 自旋锁好处：循环比较获取没有类似wait的阻塞。
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock方法自己持有锁5秒钟，B随后进来后发现
 * 当前有线程持有锁，所以只能通过自旋等待，直到A释放锁后B随后抢到。
 */
public class D02SpinLock
{
    /**
     * 使用AtomicReference实现线程安全的锁机制。
     * AtomicReference<Thread> atomicReference = new AtomicReference<>();
     */
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 获取锁。
     * 使用CAS操作尝试将当前线程设置为锁状态，直到成功获取锁才退出循环。
     */
    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "\t" + "----come in");
        while (!atomicReference.compareAndSet(null, thread)) {
            // CAS操作失败，循环尝试直到成功
        }
    }

    /**
     * 释放锁。
     * 使用CAS操作将锁状态设置为空，表示锁被释放。
     */
    public void unLock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName() + "\t" + "----task over, unLock...");
    }


    public static void main(String[] args) {
        D02SpinLock spinLockDemo = new D02SpinLock();

        new Thread(() -> {
            spinLockDemo.lock();
            //暂停几秒钟线程
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) { e.printStackTrace(); }
            spinLockDemo.unLock();
        },"A").start();

        //暂停500毫秒,线程A先于B启动
        try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

        new Thread(() -> {
            spinLockDemo.lock();

            spinLockDemo.unLock();
        },"B").start();


    }
}
