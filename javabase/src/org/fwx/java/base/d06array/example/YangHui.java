package org.fwx.java.base.d06array.example;

/**
 * [
 *  输出 10 * 10 的 杨辉三角
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/25 14:57 ]
 */
public class YangHui {
    public static void main(String[] args) {
        int[][] arrays = new int[10][];

        for (int i = 0; i < arrays.length; i++) {
            // 构造二维数组
            arrays[i] = new int[i + 1];

            // 每行的第一个和最后一个值是 1
            arrays[i][0] = 1;
            arrays[i][i] = 1;

            // 第三行开始，除去首末是 1。其他位置是上一行前一位，加上上一行当前位
            for (int j = 1; j < arrays[i].length -1; j++) {
                arrays[i][j] = arrays[i-1][j-1] +  arrays[i-1][j];
            }
        }

        // 打印数组
        for (int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++) {
                System.out.print(arrays[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
