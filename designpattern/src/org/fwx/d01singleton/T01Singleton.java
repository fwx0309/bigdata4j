package org.fwx.d01singleton;

/**
 * 单例模式：饿汉式
 *  线程安全，生命周期长，占用内存资源
 */
public class T01Singleton {
    public static void main(String[] args) {
        Singleton1 instance = Singleton1.getInstance();
        Singleton1 instance1 = Singleton1.getInstance();

        System.out.println(instance==instance1);
    }

}

class Singleton1{
    // 私有构造器
    private Singleton1(){}

    // 创建实例
    private static Singleton1 instance =  new Singleton1();

    // 获取对象方法
    public static Singleton1 getInstance(){
        return instance;
    }
}
