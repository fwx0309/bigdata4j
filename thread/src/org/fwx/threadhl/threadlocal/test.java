package org.fwx.threadhl.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName test
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/23 11:20
 * @Version 1.0
 */
public class test {

    public static void main(String[] args) throws InterruptedException {
        A a = new A();

        new Thread(()->{
            System.out.println(Thread.currentThread() +"integer1 = " + a.t1.get());
            System.out.println(Thread.currentThread() +"integer2 = " + a.t2.get());
            System.out.println(Thread.currentThread() +"integer3 = " + a.t3.get());

            a.t1.set(2);
            a.t2.set(3);
            a.t3.set(3);

            System.out.println(Thread.currentThread() +"integer1 = " + a.t1.get());
            System.out.println(Thread.currentThread() +"integer2 = " + a.t2.get());
            System.out.println(Thread.currentThread() +"integer3 = " + a.t3.get());
        },"A").start();

        new Thread(()->{
            System.out.println(Thread.currentThread() +"integer1 = " + a.t1.get());
            System.out.println(Thread.currentThread() +"integer2 = " + a.t2.get());
            System.out.println(Thread.currentThread() +"integer3 = " + a.t3.get());

            a.t1.set(4);
            a.t2.set(5);
            a.t3.set(6);

            System.out.println(Thread.currentThread() +"integer1 = " + a.t1.get());
            System.out.println(Thread.currentThread() +"integer2 = " + a.t2.get());
            System.out.println(Thread.currentThread() +"integer3 = " + a.t3.get());
        },"B").start();


    }

}

class A{

    volatile ThreadLocal<Integer> t1 = ThreadLocal.withInitial(()->0);
    volatile  ThreadLocal<Integer> t2 = ThreadLocal.withInitial(()->1);

    ThreadLocal<Integer> t3 = new ThreadLocal<>();

}
