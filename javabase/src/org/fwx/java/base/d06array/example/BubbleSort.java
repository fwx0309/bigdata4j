package org.fwx.java.base.d06array.example;

/**
 * [
 *  冒泡排序
 *      执行 n - 1 轮；
 *      单轮从左往右每次两个相邻的数做比较，比较到最后得到最大或最小的数；
 *      下一轮比较次数减一；
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/27 17:26 ]
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{43,32,76,-98,0,64,33,-21,32,99};

        // 排序前输出
        for (int a : arr) {
            System.out.print(a + "\t");
        }
        System.out.println();

        // 交换临时变量
        int tmp = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 -i; j++) {
                if (arr[j] > arr[j + 1]){
                    tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        // 排序后输出
        for (int a : arr) {
            System.out.print(a + "\t");
        }
    }
}
