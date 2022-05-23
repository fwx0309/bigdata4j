package org.fwx.thread.lock;

/**
 * 同步8锁：
 *  1.同一个对象，两个同步方法，getOne()、getTwo()                                                结果：one  two
 *  2.同一个对象，两个同步方法，getOne():sleep(3000)、getTwo()                                    结果：one  two
 *  3.同一个对象，两个同步方法，getOne():sleep(3000)、getTwo()，一个普通方法，getThree               结果：three one  two
 *  4.两个对象，两个同步方法，getOne():sleep(3000)、getTwo()                                      结果：two  one
 *  5.同一个对象，两个同步方法，static getOne():sleep(3000)、getTwo()                              结果：two  one
 *  6.同一个对象，两个同步方法，static getOne():sleep(3000)、static getTwo()                       结果：one  two
 *  7.两个对象，两个同步方法，static getOne():sleep(3000)、getTwo()                                结果：two  one
 *  7.两个对象，两个同步方法，static getOne():sleep(3000)、static getTwo()                         结果：one  two
 *
 * 线程8锁要点：
 *  1.非静态方法默认锁为this，静态方法默认锁为class对象
 *  2.某一时刻内，不管有几个方法，只能有一个线程持有锁
 */
public class Sync8Lock {
    public static void main(String[] args) {
        PrintResult pr = new PrintResult();
        PrintResult pr1 = new PrintResult();

        new Thread(new Runnable() {
            @Override
            public void run() {
                pr.getOne();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // pr.getTwo();
                pr1.getTwo();
            }
        }).start();

        /*new Thread(new Runnable() {
            @Override
            public void run() {
                pr.getThree();
            }
        }).start();*/
    }
}

class PrintResult{
    public static synchronized void getOne(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + ":one");
    }

    public static synchronized void getTwo(){
        System.out.println(Thread.currentThread().getName() + ":two");
    }

    public void getThree(){
        System.out.println(Thread.currentThread().getName() + ":three");
    }
}
