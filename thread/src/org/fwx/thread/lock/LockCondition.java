package org.fwx.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程ABC，一次打印，轮询10次
 */
public class LockCondition {
    public static void main(String[] args) {
        PrintInfo printInfo = new PrintInfo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    printInfo.printA(1,i);
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    printInfo.printB(2,i);
                }
            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    printInfo.printC(3,i);
                }
            }
        },"C").start();
    }
}

class PrintInfo {
    // 判断ABC依次执行的标记
    private int threadFlag = 1;

    private Lock lock = new ReentrantLock();

    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    /**
     * 打印A信息
     * @param batchIn 内部循环打印次数
     * @param batchOut 外部循环打印次数
     */
    public void printA(int batchIn,int batchOut) {
        lock.lock();
        try {
            // 标记不符合，等待
            if (threadFlag != 1) {
                conditionA.await();
            }

            // 符合当前标记，执行逻辑
            for (int i = 0; i < batchIn; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" + batchOut);
            }

            threadFlag = 2;
            conditionB.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印B信息
     * @param batchIn 内部循环打印次数
     * @param batchOut 外部循环打印次数
     */
    public void printB(int batchIn,int batchOut) {
        lock.lock();
        try {
            // 标记不符合，等待
            if (threadFlag != 2) {
                conditionB.await();
            }

            // 符合当前标记，执行逻辑
            for (int i = 0; i < batchIn; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" + batchOut);
            }

            threadFlag = 3;
            conditionC.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 打印C信息
     * @param batchIn 内部循环打印次数
     * @param batchOut 外部循环打印次数
     */
    public void printC(int batchIn,int batchOut) {
        lock.lock();
        try {
            // 标记不符合，等待
            if (threadFlag != 3) {
                conditionC.await();
            }

            // 符合当前标记，执行逻辑
            for (int i = 0; i < batchIn; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i + ":" + batchOut);
            }
            System.out.println("-------- over batch " + batchOut + " --------");

            threadFlag = 1;
            conditionA.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
