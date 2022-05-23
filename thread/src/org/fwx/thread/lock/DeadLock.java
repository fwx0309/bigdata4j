package org.fwx.thread.lock;

/**
 * 死锁
 *
 * 使用 jps 查看进程 id，用jstack -l 进程id，查看堆栈信息“Found 1 deadlock.”。
 */
public class DeadLock {
    public static void main(String[] args) {

        Object a = new Object();
        Object b = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (a){
                    System.out.println(Thread.currentThread().getName() + ": 持有锁a，准备获取锁b...");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (b){
                        System.out.println(Thread.currentThread().getName() + ": 持有锁b。");
                    }
                }
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (b){
                    System.out.println(Thread.currentThread().getName() + ": 持有锁b，准备获取锁a...");

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (a){
                        System.out.println(Thread.currentThread().getName() + ": 持有锁a。");
                    }
                }
            }
        },"B").start();

    }
}
