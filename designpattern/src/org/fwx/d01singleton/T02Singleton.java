package org.fwx.d01singleton;

/**
 * 单例模式：饿汉式
 *  线程不安全，调用时创建对象
 */
public class T02Singleton {
    public static void main(String[] args) {
        Singleton2 instance = Singleton2.getInstance();
        Singleton2 instance1 = Singleton2.getInstance();

        System.out.println(instance == instance1);
    }
}

/**
 * 双端检查
 */
class Singleton2{
    private static volatile Singleton2 instance = null;
    private Singleton2(){}

    public static Singleton2 getInstance(){

        if (instance == null) {
            synchronized (Singleton2.class) {
                if(instance == null){
                    instance = new Singleton2();
                }
            }
        }

        return instance;
    }
}
