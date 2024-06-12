package org.fwx.d01singleton;

/**
 * @ClassName T03Singleton
 * @Description TODO
 * @Author Fwx
 * @Date 2024/5/28 15:35
 * @Version 1.0
 */
public class T03Singleton {
    public static void main(String[] args) {
        Singleton3 instance = Singleton3.getInstance();
        System.out.println("instance = " + instance);
    }
}

class Singleton3{
    private Singleton3(){};

    private static class InnerSingleton3{
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance(){
        return InnerSingleton3.INSTANCE;
    }
}


