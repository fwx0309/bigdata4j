package org.fwx.thread;

public class D05ImplRunnableSyncCode {
    public static void main(String[] args) {

        MyThread2 myThread = new MyThread2();

        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        Thread t3 = new Thread(myThread);

        t1.start();
        t2.start();
        t3.start();
    }
}

class MyThread2 implements Runnable{

    private int num = 100;

    @Override
    public void run() {
        while (num > 0){
            synchronized (this) {
                if (num > 0){
                    System.out.println( Thread.currentThread().getName() + ":" +num);
                    num -= 1;

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
        }
    }
}