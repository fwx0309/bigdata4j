package org.fwx.java.base.d05loop;

/**
 * [
 *  For循环结构的使用
 * 一、循环结构的4个要素
 * ① 初始化条件
 * ② 循环条件  --->是boolean类型
 * ③ 循环体
 * ④ 迭代条件
 *
 * 二、for循环的结构
 *
 * for(①;②;④){
 * 	③
 * }
 *
 * 执行过程：① - ② - ③ - ④ - ② - ③ - ④ - ... - ②
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/5 8:03 ]
 */
public class ForTest01 {
    public static void main(String[] args) {
        // 初始化参数在外面
        int num = 0;
        for (;num < 5;num ++){
            System.out.println(num);
        }
    }
}
