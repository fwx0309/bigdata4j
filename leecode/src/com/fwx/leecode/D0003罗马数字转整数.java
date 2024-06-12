package com.fwx.leecode;

import java.util.HashSet;

/**
 * @ClassName D03罗马数字转整数
 * @Description 
 *  罗马数字包含以下七种字符:I，V，X，L，C，D和M。
 *
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1 。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 *
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 *
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给定一个罗马数字，将其转换成整数。
 *
 *
 * 示例1:
 *
 * 输入:s = "III"
 * 输出: 3
 * 示例2:
 *
 * 输入:s = "IV"
 * 输出: 4
 * 示例3:
 *
 * 输入:s = "IX"
 * 输出: 9
 * 示例4:
 *
 * 输入:s = "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例5:
 *
 * 输入:s = "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 15
 * s 仅含字符 ('I', 'V', 'X', 'L', 'C', 'D', 'M')
 * 题目数据保证 s 是一个有效的罗马数字，且表示整数在范围 [1, 3999(MMMCMXCIX)] 内
 * 题目所给测试用例皆符合罗马数字书写规则，不会出现跨位等情况。
 * IL 和 IM 这样的例子并不符合题目要求，49 应该写作 XLIX，999 应该写作 CMXCIX 。
 * 关于罗马数字的详尽书写规则，可以参考 罗马数字 - Mathematics 。
 *
 * @Author Fwx
 * @Date 2023/3/22 11:35
 * @Version 1.0
 */
public class D0003罗马数字转整数 {
    public static void main(String[] args) {
        D0003罗马数字转整数 d = new D0003罗马数字转整数();
        System.out.println(d.romanToInt("IXIV"));

    }

    public int romanToInt(String s){
        s = s.toUpperCase();

        if ((s.isEmpty()) || (s.length() > 15)) {
            return -1;
        }

        HashSet<Integer> set = new HashSet<>();
        set.add(73);
        set.add(86);
        set.add(88);
        set.add(76);
        set.add(67);
        set.add(68);
        set.add(77);
        //罗马数字包含以下七种字符:I，V，X，L，C，D和M。
        for (int i = 0; i < s.length(); i++) {
            int c = s.charAt(i);
            if (!set.contains(c)){
                return -1;
            }
            // I可以放在V(5) 和X(10) 的左边，来表示 4 和 9
            // X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
            // C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
            // * 字符          数值
            // * I             1
            // * V             5
            // * X             10
            // * L             50
            // * C             100
            // * D             500
            // * M             1000
            if(i < s.length() -1){
                String c1 = String.valueOf(s.charAt(i + 1));
                // 及I后面不能是LCDM
                if (c==73 && "LCDM".contains(c1)){
                    return -1;
                }
                // 及V后面不能是VXLCDM
                if (c==86 && "VXLCDM".contains(c1)){
                    return -1;
                }
                // 及X后面不能是DM
                if (c==88 && "DM".contains(c1)){
                    return -1;
                }
                // 及L后面不能是LCDM
                if (c==76 && "LCDM".contains(c1)){
                    return -1;
                }
                // 及C后面都可以
                // 及D后面不能是DM
                if (c==68 && "DM".contains(c1)){
                    return -1;
                }
                // 及M后面都可以
            }
        }

        // 求值
        int sum = 0;
        StringBuilder builder = new StringBuilder(s);
        // * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
        // * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
        // * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
        int iv = builder.indexOf("IV");
        if (iv != -1){
            sum += 4;
            builder.replace(iv,iv+2,"");
        }

        int ix = builder.indexOf("IX");
        if (ix != -1){
            sum += 9;
            builder.replace(ix,ix+2,"");
        }

        int xl = builder.indexOf("XL");
        if (xl != -1){
            sum += 40;
            builder.replace(xl,xl+2,"");
        }

        int xc = builder.indexOf("XC");
        if (xc != -1){
            sum += 90;
            builder.replace(xc,xc+2,"");
        }

        int cd = builder.indexOf("CD");
        if (cd != -1){
            sum += 400;
            builder.replace(cd,cd+2,"");
        }

        int cm = builder.indexOf("CM");
        if (cm != -1){
            sum += 900;
            builder.replace(cm,cm+2,"");
        }

        if (builder.length()>0) {
            for (int i = 0; i < builder.length(); i++) {
                int c = builder.charAt(i);
                switch (c){
                    case 77 :
                        sum += 1000;
                        break;
                    case 68:
                        sum += 500;
                        break;
                    case 67 :
                        sum += 100;
                        break;
                    case 76:
                        sum += 50;
                        break;
                    case 88 :
                        sum += 10;
                        break;
                    case 86:
                        sum += 5;
                        break;
                    case 73:
                        sum += 1;
                        break;
                }

            }
        }

        return sum;

    }
}
