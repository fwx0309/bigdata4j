package org.fwx.java.base.d03offset;

/**
 * [
 *  位运算：位移
 *
 *  结论：
 *  1. 位运算符操作的都是整型的数据
 *  2. << ：在一定范围内，每向左移1位，相当于 * 2
 *     >> :在一定范围内，每向右移1位，相当于 / 2
 *     >>> :无符号右移，左边用 0 补齐
 *
 *  面试题：最高效方式的计算2 * 8 ？  2 << 3  或 8 << 1
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/3 12:08 ]
 */
public class OffserTest01 {
    public static void main(String[] args) {
        // 21 向左位移两位
        // 00000000 00000000 00000000 00010101 == 21
        // 00000000 00000000 00000000 01010100 == 21 * 2 * 2
        int a = 21;
        a = a << 2;
        System.out.println("a = " + a);
    }
}
