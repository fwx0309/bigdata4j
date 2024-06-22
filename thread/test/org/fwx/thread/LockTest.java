package org.fwx.thread;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    public static void main(String[] args) {

        MyLock myLock = new MyLock();
        Thread thread = new Thread(myLock);
        Thread thread1 = new Thread(myLock);
        Thread thread2 = new Thread(myLock);

        thread.start();
        thread1.start();
        thread2.start();
    }
}

class MyLock implements Runnable {

    private int num = 100;

    private ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            reentrantLock.lock();
            try {
                if (num > 0) {

                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num--;

                } else {
                    break;
                }
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
