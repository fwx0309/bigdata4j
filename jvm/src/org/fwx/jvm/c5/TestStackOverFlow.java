package org.fwx.jvm.c5;

import sun.dc.path.FastPathProducer;

/**
 * StackOverflowError
 *  该函数会无限递归调用自身，导致栈溢出错误（StackOverflowError）。
 *  在每次递归调用时，会先输出当前的计数值 count，然后将 count 自增 1。
 *  由于没有终止递归的条件，程序会一直进行下去，直到栈空间耗尽。
 *
 *  运行测试：
 *      默认值： count=11421
 *      idea运行参数设置 -Xss256k：count=2456
 */
public class TestStackOverFlow {

    private static int count = 1;

    public static void main(String[] args) {
        System.out.println(count);

        count ++ ;

        main(args);
    }
}
