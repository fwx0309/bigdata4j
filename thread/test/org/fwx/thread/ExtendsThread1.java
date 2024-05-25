package org.fwx.thread;

public class ExtendsThread1 {
    public static void main(String[] args) {
        MyThread1 myThread = new MyThread1();
        myThread.start();

        System.out.println(Thread.currentThread().getName() + ":main");
    }
}

class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ": myThread");
    }
}
