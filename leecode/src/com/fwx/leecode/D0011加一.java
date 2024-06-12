package com.fwx.leecode;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/**
 * @ClassName D0011加一
 * @Description
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 * 示例1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 示例2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 *
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 *
 * @Author Fwx
 * @Date 2023/5/31 16:29
 * @Version 1.0
 */
public class D0011加一 {
    public static void main(String[] args) {
        plusOne(new int[]{1,2,3,4,5});
    }

    public static int[] plusOne(int[] digits) {
        for (int i = digits.length -1; i >= 0; i--) {
            if ((digits[i] += 1) > 9){

            } else {

            }
        }

        return new int[1];
    }
}
