package org.fwx.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁：
 * 写写、读写：互斥
 * 读读：不互斥
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReadWriteData rwData = new ReadWriteData();

        // 写数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                rwData.setNum(new Random().nextInt());
            }
        }, "write Thread").start();

        // 读数据
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rwData.getNum();
                }
            }).start();
        }
    }
}

class ReadWriteData {
    private int num = 0;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void getNum() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + ":" + num);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setNum(int num) {
        lock.writeLock().lock();
        try {
            this.num = num;
            System.out.println(Thread.currentThread().getName() + ": update data! " + num);
        } finally {
            lock.writeLock().unlock();
        }
    }
}
