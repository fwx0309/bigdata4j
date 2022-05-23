package org.fwx.thread.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 锁降级：
 *  写锁可以降级为读锁，读锁不能升级为写锁
 *
 *  下面代码 1 和 2 交换程序会卡住
 */
public class LockDemotion {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();

        // 1.获取写锁
        writeLock.lock();
        System.out.println("get writeLock!");

        // 2.获取读锁
        readLock.lock();
        System.out.println("get readLock!");

        // 3.释放写锁
        writeLock.unlock();
        System.out.println("release writeLock!");

        // 4.释放读锁
        readLock.unlock();
        System.out.println("release readLock!");
    }
}
