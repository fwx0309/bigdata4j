package org.fwx.thread;

/**
 * 线程交互，两个线程交替输出1-100的数字
 */
public class CommunicationTest {
    public static void main(String[] args) {
        CommunicateThread communicateThread = new CommunicateThread();
        Thread thread1 = new Thread(communicateThread);
        Thread thread2 = new Thread(communicateThread);

        thread1.start();
        thread2.start();
    }
}

class CommunicateThread implements Runnable {
    private int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (num <= 100) {
                    notify();

                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;

                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    notify();
                    break;
                }
            }
        }
    }
}
