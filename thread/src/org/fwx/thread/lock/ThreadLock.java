package org.fwx.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadLock {
    public static void main(String[] args) {
        MyThread4 myThread4 = new MyThread4();

        Thread t1 = new Thread(myThread4);
        Thread t2 = new Thread(myThread4);

        t1.start();
        t2.start();
    }
}

class MyThread4 implements Runnable {
    private int num = 100;
    private ReentrantLock lock = new ReentrantLock(true);

    @Override
    public void run() {
        while (num > 0) {
            lock.lock();
            try {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num -= 1;
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
