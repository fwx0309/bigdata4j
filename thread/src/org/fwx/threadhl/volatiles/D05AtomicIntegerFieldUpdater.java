package org.fwx.threadhl.volatiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @ClassName D05AtomicReferenceFieldUpdater
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/23 8:07
 * @Version 1.0
 */
public class D05AtomicIntegerFieldUpdater {
    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <=10; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <=1000; j++) {
                        //bankAccount.add();
                        bankAccount.transMoney(bankAccount);
                    }
                } finally {
                    countDownLatch.countDown();
                }
            },String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName()+"\t"+"result: "+bankAccount.money);
    }
}

class BankAccount {
    String bankName = "CCB";

    //更新的对象属性必须使用 public volatile 修饰符。
    public volatile int money = 0;//钱数

    public void add() {
        money++;
    }

    //因为对象的属性修改类型原子类都是抽象类，所以每次使用都必须
    // 使用静态方法newUpdater()创建一个更新器，并且需要设置想要更新的类和属性。

    AtomicIntegerFieldUpdater<BankAccount> fieldUpdater =
            AtomicIntegerFieldUpdater.newUpdater(BankAccount.class,"money");

    //不加synchronized，保证高性能原子性，局部微创小手术
    public void transMoney(BankAccount bankAccount) {
        fieldUpdater.getAndIncrement(bankAccount);
    }



}


