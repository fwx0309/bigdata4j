package org.fwx.jvm;

/**
 * @ClassName D01StringTest
 * @Description 不同情况下，字符串常量池中存在相同的字符串，那么，字符串常量池中的字符串和堆中的字符串是否相等？
 * @Author Fwx
 * @Date 2024/5/13 16:06
 * @Version 1.0
 */
public class D01StringTest {

    public static void main(String[] args) {
//        String s1 = "helloworld";
//        String s2 = new String("helloworld").intern();
//        System.out.println(s1 == s2); // false

//        String s3 = new String("helloworld").intern();
//        String s4 = "helloworld";
//        System.out.println(s3 == s4); // true

//        String s5 = "helloworld";
//        String s6 = new String("hello") + new String("world");
//        s6.intern();
//        System.out.println(s5 == s6);   //

        String s7 = new String("hello") + new String("world");
        s7.intern();
        String s8 = "helloworld";
        System.out.println(s7 == s8);   //
    }
}
