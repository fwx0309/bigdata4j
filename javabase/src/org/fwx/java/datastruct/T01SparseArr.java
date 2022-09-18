package org.fwx.java.datastruct;

import org.junit.Before;
import org.junit.Test;

/**
 * [
 *  稀疏数组：用于数据压缩
 *      1.二维数组压缩成稀疏数组
 *      2.稀疏数组还原成二维数据
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/8/3 14:46 ]
 */
public class T01SparseArr {

    private int[][] arrs;

    @Before
    public void init(){
        // 初始化二维数组
        arrs = new int[11][12];

        arrs[1][2] = 1;
        arrs[2][3] = 2;
        arrs[3][4] = 3;
        arrs[7][8] = 4;

        // 二维数组
        System.out.println("------------------------- 二维数组 -------------------------");
        for (int[] rows : arrs) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        System.out.println();
    }

    /**
     * 1.二维数组转稀疏数组
     * 2.稀疏数组还原成二维数据
     */
    @Test
    public void test(){

        // *** 1.二维数组转稀疏数组
        System.out.println("二维数组转稀疏数组");
        // 有效数据个数
        int sum = 0;
        System.out.println("sum = " + sum);
        int rowsNum = arrs.length;
        System.out.println("rowsNum = " + rowsNum);
        int columnNum = arrs[0].length;
        System.out.println("columnNum = " + columnNum);

        for (int[] rows : arrs) {
            for (int data : rows) {
                if(0 != data){
                    sum += 1;
                }
            }
        }

        // 创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];

        // 往稀疏数组填充第一行，及原数组信息：行、列、有效数据量
        sparseArr[0][0] = rowsNum;
        sparseArr[0][1] = columnNum;
        sparseArr[0][2] = sum;

        // 往稀疏数组填充数据的标记
        int sparseArrNum = 1;
        // 往稀疏数组填充数据
        for (int i = 0; i < rowsNum; i++) {
            for (int j = 0; j < columnNum; j++) {
                if (0 != arrs[i][j] && sparseArrNum <= sum ) {
                    sparseArr[sparseArrNum][0] = i;
                    sparseArr[sparseArrNum][1] = j;
                    sparseArr[sparseArrNum][2] = arrs[i][j];
                    sparseArrNum ++;
                }
            }
        }

        // 稀疏数组
        System.out.println("------------------------- 稀疏数组 -------------------------");
        for (int[] rows : sparseArr) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------");
        System.out.println();
        System.out.println();


        // *** 2.稀疏数组还原成二维数据
        int[][] dsArr = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            dsArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        // 稀疏数组
        System.out.println("------------------------- 还原后的二维数组 -------------------------");
        for (int[] rows : dsArr) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------------------------------");
    }
}
