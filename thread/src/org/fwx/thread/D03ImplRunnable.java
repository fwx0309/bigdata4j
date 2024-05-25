package org.fwx.thread;

public class D03ImplRunnable {
    public static void main(String[] args) {

        MyThread1 myT1 = new MyThread1();

        Thread t1 = new Thread(myT1);
        Thread t2 = new Thread(myT1);
        Thread t3 = new Thread(myT1);

        t1.start();
        t2.start();
        t3.start();
    }
}

class MyThread1 implements Runnable{

    private int num = 100;

    @Override
    public void run() {
        while (num>0){
            System.out.println( Thread.currentThread().getName() + ":" +num);
            num -= 1;
        }
    }
}