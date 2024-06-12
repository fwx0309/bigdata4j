package com.fwx.leecode;

/**
 * @ClassName D05有效的括号
 * @Description 
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 * 
 * 示例 1：
 *
 * 输入：s = "()"
 * 输出：true
 * 示例2：
 *
 * 输入：s = "()[]{}"
 * 输出：true
 * 示例3：
 *
 * 输入：s = "(]"
 * 输出：false
 * 
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 *
 * @Author Fwx
 * @Date 2023/3/29 8:32
 * @Version 1.0
 */
public class D0005有效的括号 {
    public static void main(String[] args) {
        // true
//        System.out.println("isValid = " + isValid("()[]{}()[]{}"));
        // true
//        System.out.println("isValid = " + isValid("{[()]}"));
        // false
        System.out.println("isValid = " + isValid("([)]"));
        // true
//        System.out.println("isValid = " + isValid("{}[{}]((){})(){}"));
    }

    public static boolean isValid(String s) {
        if (s.length() < 2) {
            return false;
        }

        StringBuilder stringBuilder = new StringBuilder(s);

        // 去除左右对应符号相邻的数据
        String replace = stringBuilder.toString().replace("()", "").replace("[]", "").replace("{}", "");
        while (true){
            replace = replace.replace("()", "").replace("[]", "").replace("{}", "");

            if (!replace.contains("()") && !replace.contains("[]") && !replace.contains("{}")){
                break;
            }
        }
        if (replace.length() > 0){
            return false;
        }

        return true;
    }
}
