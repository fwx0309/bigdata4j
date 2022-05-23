package org.fwx;

/**
 * 条件断点：先打上断点，然后右键断点写上条件。
 *  例如 for 循环直接调到第n次.
 */
public class ConditionBreakpoint {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int num = 1/0;
            System.out.println("i = " + i);
        }
    }
}
