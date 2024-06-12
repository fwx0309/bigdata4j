package org.fwx.jvm.c5;

import sun.dc.path.FastPathProducer;

/**
 * StackOverflowError
 * 默认值： count=11421
 * 设置 -Xss256k：count=2456
 */
public class TestStackOverFlow {

    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);

        count ++ ;

        main(args);
    }
}
