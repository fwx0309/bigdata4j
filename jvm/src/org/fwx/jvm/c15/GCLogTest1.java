package org.fwx.jvm.c15;

/**
 * 在jdk7 和 jdk8中分别执行
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC
 *
 * 程序执行gc过程：按顺序存入数据，当准备存入4m的数据时
 *  jdk7中情况：年轻代gc后将3个2m数据移动到老年代，4m的数据存入年轻代
 *  jdk8中情况：年轻代不gc，直接将4m的数据存老年代
 */
public class GCLogTest1 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];
    }

    public static void main(String[] agrs) {
        testAllocation();
    }
}
