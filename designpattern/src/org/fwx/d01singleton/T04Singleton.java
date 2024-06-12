package org.fwx.d01singleton;

/**
 * @ClassName T04Singleton
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/28 15:48
 * @Version 1.0
 */
public class T04Singleton {
    public static void main(String[] args) {
        Singleton4 instance = Singleton4.INSTANCE;
        Singleton4 instance1 = Singleton4.INSTANCE;
        System.out.println(instance == instance1);
        instance.doSomething();
    }
}

enum Singleton4 {
    INSTANCE;

    public void doSomething() {
        System.out.println("do something");
    }
}
