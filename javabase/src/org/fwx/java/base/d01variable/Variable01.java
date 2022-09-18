package org.fwx.java.base.d01variable;

/**
 * [
 *  变量：
 *      1.8种基本类型，
 *          byte:
 *              1字节 = 8bit（位，及 8 位 0 或 1，存储的数据）
 *              范围：-128 ~ 127
 *          short：2字节
 *          int: 4字节
 *          long: 8字节
 *          float: 4字节， 单精度 7 位有效数字，E38
 *          double: 8字节， 双精度 E308
 *          char: 2字节
 *          boolean: true / false
 *
 *          byte、short、char 之间运算，一律用int接收
 *
 *      2.3种应用类型
 *          类 (class):
 *          接口 (interface):
 *          数组 (array):
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/9/17 17:17 ]
 */
public class Variable01 {
    public static void main(String[] args) {
        byte b1 = 127;
        short s1 = 100;
        int i1 = 101;
        long l1 = 102L;
        float f1 = 10.1F;
        double d1 = 10.2;
        char c1 = 'a';
        char c2 = '\u0041';
        System.out.println("c2 = " + c2);
        char c3 = '\n';
        boolean boo1 = true; // false
    }
}
