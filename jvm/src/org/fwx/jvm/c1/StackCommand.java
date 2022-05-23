package org.fwx.jvm.c1;

/**
 * javap 查看编译后的 class 指令
 *
 * javap -v StackCommand.class
 */
public class StackCommand {
    public static void main(String[] args) {
        int i = 2;
        int j = 3;
        int k = i + j;
    }
}
