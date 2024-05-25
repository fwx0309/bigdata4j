package org.fwx.threadhl.rwlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName test
 * @Description 写锁降级为读锁后，其他线程的读锁也可以获取到读锁
 * @Author Fwx
 * @Date 2024/5/24 10:28
 * @Version 1.0
 */
public class D02ReentrantReadWriteLock {
    private static ReentrantReadWriteLock lock =new ReentrantReadWriteLock();
    public static void main(String[] args) throws InterruptedException {
        // 线程A
        new Thread(()->{
            try {
                try {
                    lock.writeLock().lock();
                    System.out.println(Thread.currentThread().getName()+":write...ing");

                    lock.readLock().lock();
                    System.out.println(Thread.currentThread().getName()+":read...ing");

                    try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
                } finally {
                    // 释放写锁后，写锁降级为读锁，其它线程的读锁也可以获取到读锁
                    lock.writeLock().unlock();
                    System.out.println(Thread.currentThread().getName()+":write...end");
                }
            } finally {
                try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
                lock.readLock().unlock();
                System.out.println(Thread.currentThread().getName()+":read...end");
            }
        },"A").start();


        TimeUnit.SECONDS.sleep(1);

        // 线程B
        new Thread(()->{

            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName()+":read...ing");

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            lock.readLock().unlock();
            System.out.println(Thread.currentThread().getName()+":read...end");
        },"B").start();
    }
}


