package org.fwx.threadhl.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * @auther zzyy
 * @create 2022-01-22 12:45
 */
public class D01VolatileSee
{
    //static boolean flag = true;
    static volatile boolean flag = true;

    /**
     * volatile ：保证可见性，及时通知其他线程，主物理内存的值已经被修改，不能保证多个线程共同修改数据时的原子性。
     * 如果没有用 volatile 修饰，t1线程是永远拿不到flag的最新值，因为t1线程是拿到的flag的副本，t1线程是拿不到主物理内存的最新值
     *
     * 内存屏障：
     *  volatile写之前的操作，都禁止重排序到 volatile 之后
     *  volatile读之后的操作，都禁止重排序到 volatile 之前
     *  volatile写之后volatile读，禁止重排序
     *
     * @param args
     */
    public static void main(String[] args)
    {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName()+"\t -----come in");
            while(flag)
            {

            }
            System.out.println(Thread.currentThread().getName()+"\t -----flag被设置为false，程序停止");
        },"t1").start();

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        flag = false;

        System.out.println(Thread.currentThread().getName()+"\t 修改完成flag: "+flag);


    }
}
