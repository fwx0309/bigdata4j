package org.fwx.java.base.d03offset;

/**
 * [
 *  位运算：异或
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/3 13:35 ]
 */
public class OffsetTest02 {
    public static void main(String[] args) {
        // 练习：交换两个变量的值
        int num1 = 10;
        int num2 = 20;
        System.out.println("num1 = " + num1 + ",num2 = " + num2);

        // 方式一：定义临时变量的方式
        // 推荐的方式
//        int tmp = num1;
//        num1 = num2;
//        num2 = tmp;

        // 方式二：好处：不用定义临时变量
        // 弊端：① 相加操作可能超出存储范围 ② 有局限性：只能适用于数值类型
        //num1 = num1 + num2;
        //num2 = num1 - num2;
        //num1 = num1 - num2;

        // 方式三：使用位运算符   num1 ^ num2 ^ num2 = num1
        // 有局限性：只能适用于数值类型
        num1 = num1 ^ num2;
        num2 = num1 ^ num2;
        num1 = num1 ^ num2;

        System.out.println("num1 = " + num1 + ",num2 = " + num2);

    }
}
