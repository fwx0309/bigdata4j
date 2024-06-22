package org.fwx.thread;

public class ImplRunnableTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());

        MyImplRunnable runnable = new MyImplRunnable();
        Thread thread = new Thread(runnable);
        thread.start();
    }
}

class MyImplRunnable implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
