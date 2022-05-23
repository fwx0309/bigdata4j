package org.fwx.thread.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 多线程原子操作共享值
 */
public class AtomicAttribute {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);

        SerializeNum serializeNum = new SerializeNum();

        for (int i = 0; i < 10; i++) {
            service.execute(serializeNum);
        }

        service.shutdown();
    }
}

class SerializeNum implements Runnable {
    private AtomicInteger serializeNum = new AtomicInteger();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +":"+ getSerializeNum());
    }

    public int getSerializeNum() {
        return serializeNum.getAndIncrement();
    }
}
