package org.fwx.thread;

/**
 * volatile 关键字解决内存可见性问题
 * 不同于 synchronized，volatile 不具备互斥性
 */
public class VolatileField {
    public static void main(String[] args) {
        MyThread6 thread = new MyThread6();
        new Thread(thread).start();

        while (true) {
            if (thread.isFlag()) {
                System.out.println(Thread.currentThread().getName() + ":" + thread.isFlag());
                break;
            }
        }
    }
}

class MyThread6 implements Runnable {

    private volatile boolean flag = false;

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println(Thread.currentThread().getName() + ":" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
