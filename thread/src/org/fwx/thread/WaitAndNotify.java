package org.fwx.thread;

public class WaitAndNotify {
    public static void main(String[] args) {
        Cleck cleck = new Cleck();

        Producer producer = new Producer(cleck);
        Consumer consumer = new Consumer(cleck);

        Thread p1 = new Thread(producer);
        p1.setName("生产者1");
        Thread p2 = new Thread(producer);
        p2.setName("生产者2");
        Thread c1 = new Thread(consumer);
        c1.setName("消费者1");
        Thread c2 = new Thread(consumer);
        c2.setName("消费者2");

        p1.start();
        p2.start();
        c1.start();
        c2.start();
    }
}

class Cleck{

    private int num = 0;

    public synchronized void exeProducer() {
        if(num<20){
            num ++;
            System.out.println(Thread.currentThread().getName() + ": 生产第" + num + "个数据");

            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void exeConsumer() {
        if(num>0){
            System.out.println(Thread.currentThread().getName() + ": 消费第" + num + "个数据");
            num --;

            notify();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Producer implements Runnable{

    private Cleck cleck;

    public Producer(Cleck cleck){
        this.cleck = cleck;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleck.exeProducer();
        }
    }
}

class Consumer implements Runnable{

    private Cleck cleck;

    public Consumer(Cleck cleck){
        this.cleck = cleck;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cleck.exeConsumer();
        }
    }
}
