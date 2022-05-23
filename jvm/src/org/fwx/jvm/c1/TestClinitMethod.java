package org.fwx.jvm.c1;

/**
 * class 文件中的 clinit 只有在对象有静态属性或静态代码块时才会生成
 */
public class TestClinitMethod {
    private static int num = 1;

    static {
       int num = 2;
    }

    public static void main(String[] args) {
        System.out.println(1);
    }
}
