package org.fwx.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ArrayBlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

//        String[] strings = new String[]{"a", "b", "c"};
        String[] strings = new String[]{"a", "b", "c", "d"};

        // 1.第一组，添加或取出数据，超过队列长度会抛出异常
        /*for (String string : strings) {
            System.out.println(queue.add(string));
            //System.out.println(queue.element());
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.remove());
        }*/

        // 2.第二组，添加或取出数据，超过队列长度会返回特殊值
        /*for (String string : strings) {
            System.out.println(queue.offer(string));
            //System.out.println(queue.peek());
        }

        for (int i = 0; i < 4; i++) {
            System.out.println(queue.poll());
        }*/

        // 3.第三组，添加或取出数据，超过队列长度会阻塞线程
        /*for (String string : strings) {
            queue.put(string);
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.take());
        }*/

        // 3.第四组，添加或取出数据，超过队列长度会阻塞线程，并可以设置阻塞时间
        for (String string : strings) {
            queue.offer(string,3, TimeUnit.SECONDS);
        }

        for (int i = 0; i < 3; i++) {
            System.out.println(queue.poll(3,TimeUnit.SECONDS));
        }
    }
}
