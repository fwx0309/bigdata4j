package com.fwx.leecode;

import java.util.Stack;

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
public class D0005有效的括号_优化 {
    public static void main(String[] args) {
        // true
//        System.out.println("isValid = " + isValid("()[]{}()[]{}"));
        // true
        System.out.println("isValid = " + isValid("{[()]}"));
        // false
//        System.out.println("isValid = " + isValid("([)]"));
        // true
//        System.out.println("isValid = " + isValid("{}[{}]((){})(){}"));
    }

    public static boolean isValid(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        if(stack.empty())
            return true;
        return false;
    }
}
