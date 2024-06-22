package org.fwx.jvm.c5;

/**
 * @ClassName StackVariableTest
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/9 22:36
 * @Version 1.0
 */
public class StackVariableTest {
    public static void main(String[] args) {
        System.out.println("hello world");
    }

    public void test1() {
        int num = 10;
        System.out.println(num);
    }
}
