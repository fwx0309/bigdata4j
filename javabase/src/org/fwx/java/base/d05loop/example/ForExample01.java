package org.fwx.java.base.d05loop.example;

import java.util.Scanner;

/**
 * [
 *  题目：输入两个正整数m和n，求其最大公约数和最小公倍数。
 *  比如：12和20的最大公约数是4，最小公倍数是60。
 *
 *  说明：break关键字的使用：一旦在循环中执行到break，就跳出循环
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/6 10:37 ]
 */
public class ForExample01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入第一个正整数");
        int x = scanner.nextInt();
        System.out.println("请输入第二个正整数");
        int y = scanner.nextInt();

        if(x == y) {
            System.out.println("输入的两个数相等！");
            // 0：正常退出，1：非正常退出
            System.exit(0);
        }

        int max = 0;
        int min = 0;
        if(x > y){
            max = x;
            min = y;
        } else {
            max = y;
            min = x;
        }

        // 最大公约数
        for (int i = min; i > 0; i--){
            if (x%i==0 && y%i==0){
                System.out.println("最大公约数为：" + i);
                break;
            }
        }

        // 最小公倍数
        for (int i = 1; max * i <= x * y; i++) {
            int multiple = max * i;
            if (multiple % x == 0 && multiple % y == 0) {
                System.out.println("最小公倍数为：" + multiple);
                break;
            }
        }


    }
}
