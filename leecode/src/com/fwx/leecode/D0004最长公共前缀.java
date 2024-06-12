package com.fwx.leecode;

/**
 * @ClassName D04最长公共前缀
 * @Description
 *  编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串""。
 *
 *
 * 示例 1：
 *
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * 示例 2：
 *
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * 
 *
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 *
 * @Author Fwx
 * @Date 2023/3/22 17:09
 * @Version 1.0
 */
public class D0004最长公共前缀 {

    public static void main(String[] args) {
//        System.out.println(longestCommonPrefix(new String[]{"flower", "flow", "flight"}));
//        System.out.println(longestCommonPrefix(new String[]{"dog","racecar","car"}));
//        System.out.println(longestCommonPrefix(new String[]{"","b"}));
        System.out.println(longestCommonPrefix(new String[]{"a","ac"}));
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        if (strs.length == 1){
            return strs[0];
        }

        int index = 0;
        int length = 0;
        for (int i = 0; i < strs.length; i++) {
            int l = strs[i].length();
            if (index == 0){
                length = l;
                index ++;
            }
            if (length > l) {
                index = i;
                length = l;
            }
        }

        if(length == 0){
            return "";
        }

        StringBuilder result = new StringBuilder();
        // 第一次层循环：获取每个字符串的第几位字符
        for (int i = 0; i < length; i++) {
            // 第二次层循环：遍历字符串数组
            for (int j = 0; j < strs.length; j++) {

                char c = strs[j].charAt(i);
                // 把第一字符串的值，当做初始比较值
                if (j == 0) {
                    result.append(c);
                } else {
                    if (result.charAt(i) != c) {
                        result.replace(i,i+1,"");
                        return result.toString();
                    }
                }
            }
        }
        return result.toString();
    }
}
