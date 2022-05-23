package org.fwx.thread;

/**
 * 使用继承 Thread 实现多线
 * 在一个线程中 join 另外一个线程
 */
public class ExtendsThread {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.setName("MyThread");

        /**
         * 匿名线程类
         */
        new Thread("NiMingThread"){
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() +":"+ i);
                    }

                    // *** 释放执行权
                    /*if(i == 11){
                        yield();
                    }*/

                    // join, MyThread加入后，需要等MyThread执行完，才会继续执行NiMingThread
                    if(i == 5){
                        try {
                            myThread.join();
                            System.out.println("MyThread isAlive(start):"+myThread.isAlive());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        myThread.start();

        System.out.println("MyThread isAlive(end):"+myThread.isAlive());
    }
}

/**
 * 自定义线程类
 */
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() +":"+ i);
            }

            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
