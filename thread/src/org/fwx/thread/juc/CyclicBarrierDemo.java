package org.fwx.thread.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.FutureTask;

/**
 * 循环栅栏
 *
 * 达到指定的运行次数后，会执行 new CyclicBarrier{} 方法体中的代码
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, ()->{
            System.out.println("over !");
        });

        CBThread cbThread = new CBThread(cyclicBarrier);

        for (int i = 1; i <=14; i++) {
            new Thread(new FutureTask<Integer>(cbThread),String.valueOf(i)).start();
        }


    }
}

class CBThread implements Callable {

    private CyclicBarrier cyclicBarrier;

    public CBThread(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName() + ":execute " );
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
