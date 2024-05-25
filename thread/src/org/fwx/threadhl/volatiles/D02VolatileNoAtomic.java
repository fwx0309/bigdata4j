package org.fwx.threadhl.volatiles;

import java.util.concurrent.TimeUnit;

class MyNumber
{
    volatile int number;

    public void addPlusPlus()
    {
        number++;
    }
}

/**
 * 如果线程操作共享数据，没有使用同步机制，就会有数据不一致问题
 */
public class D02VolatileNoAtomic
{
    public static void main(String[] args)
    {
        MyNumber myNumber = new MyNumber();

        for (int i = 1; i <=10; i++) {
            new Thread(() -> {
                for (int j = 1; j <=1000; j++) {
                    myNumber.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println(myNumber.number);

    }
}
