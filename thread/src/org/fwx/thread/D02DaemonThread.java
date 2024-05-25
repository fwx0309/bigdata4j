package org.fwx.thread;

/**
 * 守护线程：用户线程都结束后，jvm会退出。
 */
public class D02DaemonThread {
    public static void main(String[] args) {
        Thread daemon = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ":" + Thread.currentThread().isDaemon());

            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "...");
            }
        });
        // 设置为守护线程
        daemon.setName("daemon");
        daemon.setDaemon(true);
        daemon.start();

        System.out.println(Thread.currentThread().getName() + " over!");
        // 5 秒后用户线程退出， daemon 守护线程也会自己退出
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
