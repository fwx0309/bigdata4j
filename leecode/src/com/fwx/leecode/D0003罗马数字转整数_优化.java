package com.fwx.leecode;

/**
 * @ClassName D03罗马数字转整数
 * @Description 罗马数字包含以下七种字符:I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。
 * <p>
 * <p>
 * 示例1:
 * <p>
 * 输入:s = "III"
 * 输出: 3
 * 示例2:
 * <p>
 * 输入:s = "IV"
 * 输出: 4
 * 示例3:
 * <p>
 * 输入:s = "IX"
 * 输出: 9
 * 示例4:
 * <p>
 * 输入:s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例5:
 * <p>
 * 输入:s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999(MMMCMXCIX)] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 * @Author Fwx
 * @Date 2023/3/22 11:35
 * @Version 1.0
 */
public class D0003罗马数字转整数_优化 {
    public static void main(String[] args) {
        D0003罗马数字转整数_优化 d = new D0003罗马数字转整数_优化();
        System.out.println(d.romanToInt("IXIV"));

    }

    public int romanToInt(String s) {
        int sum = 0;

        // * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
        // * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
        // * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
        s = s.replace("IV", "a");
        s = s.replace("IX", "b");
        s = s.replace("XL", "c");
        s = s.replace("XC", "d");
        s = s.replace("CD", "e");
        s = s.replace("CM", "f");

        for (int i = 0; i < s.toCharArray().length; i++) {
            switch (s.charAt(i)) {
                case 'a': sum += 4;break;
                case 'b': sum += 9;break;
                case 'c': sum += 40;break;
                case 'd': sum += 90;break;
                case 'e': sum += 400;break;
                case 'f': sum += 900;break;
                case 'I': sum += 1;break;
                case 'V': sum += 5;break;
                case 'X': sum += 10;break;
                case 'L': sum += 50;break;
                case 'C': sum += 100;break;
                case 'D': sum += 500;break;
                case 'M': sum += 1000;break;
            }
        }

        return sum;
    }
}
