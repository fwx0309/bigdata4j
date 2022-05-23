package org.fwx.jvm.c8;

public class HeapTest {
    public static void main(String[] args) {
        System.out.println("Start...");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
