package org.fwx.jvm.c8;

/**
 * 堆内存设置
 *
 * -Xms20m -Xmx20m
 */
public class HeapTest1 {
    public static void main(String[] args) {
        System.out.println("Start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
