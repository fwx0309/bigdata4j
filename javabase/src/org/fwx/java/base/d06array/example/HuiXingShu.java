package org.fwx.java.base.d06array.example;

/**
 * [
 *  回形数：
 *      1   2   3   4
 *      12  13  14  5
 *      11  16  15  6
 *      10  9   8   7
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/26 17:48 ]
 */
public class HuiXingShu {
    public static void main(String[] args) {
        // 自定义数
        int num = 9;

        // 创建二维数组
        int[][] array = new int[num][num];

        // 循环总次数
        int poolNum = num * num;

        // 二维数组的脚标
        int x = 0;
        int y = 0;

        // 上下左右四个标记，up/down/right/left
        String direction = "right";

        // 循环赋值
        for (int i = 1; i <= poolNum; i++) {
            if("right".equalsIgnoreCase(direction)){
                if(y<num && array[x][y] == 0){
                    array[x][y] = i;
                    y ++;
                } else {
                    direction = "down";
                    y --;
                    x ++;
                }
            }

            if ("down".equalsIgnoreCase(direction)){
                if(x<num && array[x][y] == 0){
                    array[x][y] = i;
                    x ++;
                } else {
                    direction = "left";
                    x --;
                    y --;
                }
            }

            if ("left".equalsIgnoreCase(direction)) {
                if(y>=0 && array[x][y] == 0){
                    array[x][y] = i;
                    y --;
                } else {
                    direction = "up";
                    y ++;
                    x --;
                }
            }

            if ("up".equalsIgnoreCase(direction)) {
                if(x>=0 && array[x][y] == 0){
                    array[x][y] = i;
                    x --;
                } else {
                    direction = "right";
                    x ++;
                    y ++;
                    i --;
                }
            }
        }

        // 打印数组
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
                System.out.print("\t");
            }
            System.out.println();
        }
    }
}
