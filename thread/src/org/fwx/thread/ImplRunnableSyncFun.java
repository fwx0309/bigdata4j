package org.fwx.thread;

public class ImplRunnableSyncFun {
    public static void main(String[] args) {

        MyThread3 myThread = new MyThread3();

        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);
        Thread t3 = new Thread(myThread);

        t1.start();
        t2.start();
        t3.start();
    }
}

class MyThread3 implements Runnable{

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
                }
            }
        }
    }
}