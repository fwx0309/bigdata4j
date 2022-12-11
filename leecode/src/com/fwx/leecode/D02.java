package com.fwx.leecode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * [
 *  回文数
 *      给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 *      回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *      例如，121 是回文，而 123 不是。
 *
 *      示例 1：
 *      输入：x = 121
 *      输出：true
 *
 *      示例2：
 *      输入：x = -121
 *      输出：false
 *      解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 *      示例 3：
 *      输入：x = 10
 *      输出：false
 *      解释：从右向左读, 为 01 。因此它不是一个回文数。
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/11/17 11:17 ]
 */
public class D02 {
    public static void main(String[] args) {
        System.out.println(isPalindrome1(-10022001));
    }

    public static boolean isPalindrome(int x) {
        if ( x < 0){
            return false;
        }

        // 只有个位数时
        if (x < 10 && x % 10 == 0){
            return true;
        }

        // 10 的倍数为false
        if (x % 10 == 0){
            return false;
        }

        ArrayList<Integer> list = new ArrayList<>();
        while (true){
            if(0==x) {
                break;
            }
            list.add(x % 10);
            x = x / 10;
        }

        boolean flag = true;
        int head = 0;
        int end = list.size() - 1;
        while (end > head) {
            if (!list.get(head).equals(list.get(end))){
                flag = false;
                break;
            }
            head ++;
            end --;
        }

        return flag;
    }

    public static boolean isPalindrome1(int x) {
        String s = String.valueOf(x);
        String reverse = new StringBuffer(s).reverse().toString();
        return reverse.equals(s);
    }
}
