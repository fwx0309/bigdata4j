package org.fwx.java.base.d06array.example;

/**
 * [ 二分查找 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/27 15:06 ]
 */
public class ErFenChaZhao {
    public static void main(String[] args) {
        int destNum = 54;

        int[] arrays = {-98, -34, 2, 34, 54, 66, 79, 105, 210, 333};

        int middle = 0;

        int headIndex = 0;
        int endIndex =arrays.length-1;

        while (true) {
            middle = (headIndex + endIndex) /2;

            if (arrays[middle] == destNum) {
                System.out.println("index:" + middle);
                break;
            } else if (arrays[middle] > destNum) {
                endIndex = middle - 1;
            } else {
                headIndex = middle + 1;
            }

            if (headIndex > endIndex) {
                System.out.println("missed !!!");
                break;
            }
        }
    }
}
