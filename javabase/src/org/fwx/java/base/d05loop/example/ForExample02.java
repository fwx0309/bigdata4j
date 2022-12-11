package org.fwx.java.base.d05loop.example;

import java.util.Scanner;

/**
 * [ 打印菱形 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/6 18:25 ]
 */
public class ForExample02 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个正整数:");
        int num = scanner.nextInt();

        // 菱形上半部分
        int half = num - 1;
        for (int i = 1; i <= num; i++) {
            // 打印缩进
            for (int m =half; m > 0; m--) {
                System.out.print(" ");
            }

            // 打印 *
            String xing = "";
            for (int j = 1; j <= i; j++) {
                xing += "* ";
            }
            System.out.print(xing.trim());

            half--;

            System.out.println("");
        }

        // 菱形下半部分
        int half1 = num - 1;
        for (int i = 1; i <= num - 1; i++) {
            // 打印缩进
            for (int n = 1; n <= i; n++) {
                System.out.print(" ");
            }

            // 打印 *
            String xing = "";
            for (int k = half1; k > 0; k--) {
                xing += "* ";
            }
            System.out.print(xing.trim());

            half1--;

            System.out.println("");
        }

        System.out.println("-------------");
    }
}
