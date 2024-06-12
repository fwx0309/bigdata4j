package com.fwx.leecode;

import java.util.ArrayList;

/**
 * @ClassName D0012找出字符串中第一个匹配项的下标
 * @Description
 *  给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。如果needle 不是 haystack 的一部分，则返回 -1 。
 *
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配。
 * 第一个匹配项的下标是 0 ，所以返回 0 。
 *
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 *
 * 提示：
 * 1 <= haystack.length, needle.length <= 104
 * haystack 和 needle 仅由小写英文字符组成
 *
 * @Author Fwx
 * @Date 2023/7/26 9:59
 * @Version 1.0
 */
public class D0012找出字符串中第一个匹配项的下标 {

    public static void main(String[] args) {
//        int i = strStr("sadbutsad", "sad");
//        int i = strStr("a", "a");
        int i = strStr("mississippi", "issip");
        System.out.println("i = " + i);
    }

    public static int strStr(String haystack, String needle) {

        if (needle.length() == 0) {
            return -1;
        }

        if(haystack.equals(needle)){
            return 0;
        }

        char destFirst = needle.charAt(0);
        int destLength = needle.length();

        int haystackLength = haystack.length();

        for (int i = 0; i < haystack.length(); i++) {
            // 用目标字符串的第一位去匹配，获取下标
            if (destFirst == haystack.charAt(i)){
                // 用目标字符串的长度，校验原始字符串的长度是否符合
                if ((haystackLength - i) >= destLength){
                    // 比较原始字符串中截取出来的数据和目标字符串是否一致
                    String substring = haystack.substring(i, i + destLength);
                    if (needle.equals(substring)){
                        return i;
                    }
                }
            }
        }

        return -1;
    }
}
