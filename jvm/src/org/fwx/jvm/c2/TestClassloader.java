package org.fwx.jvm.c2;

public class TestClassloader {
    public static void main(String[] args) {

        // 获取系统加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);
        
        // 获取扩展加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);
        
        // 尝试获取引导类加载器
        ClassLoader bootstrapClassloader = extClassLoader.getParent();
        System.out.println(bootstrapClassloader);

        // 获取当前类加载器
        ClassLoader classLoader = TestClassloader.class.getClassLoader();
        System.out.println(classLoader);

        // 获取String类加载器
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }
}
