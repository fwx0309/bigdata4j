package org.fwx.jvm.c8;

import java.util.Date;

/**
 * 堆内存设置
 *
 * -Xms10m -Xmx10m
 */
public class HeapTest {
    public static void main(String[] args) {
        Date date = new Date();
        System.out.println("Start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
