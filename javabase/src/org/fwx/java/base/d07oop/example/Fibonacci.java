package org.fwx.java.base.d07oop.example;

/**
 * [
 *  斐波那契数列
 *      1 1 2 3 5 8 13 21 34 55
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/30 11:45 ]
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(getFibonacci(10));
    }

    public static int getFibonacci(int num) {
        if (num == 1 || num == 2) {
            return 1;
        } else {
            return (getFibonacci(num - 1) + getFibonacci(num - 2));
        }
    }
}
