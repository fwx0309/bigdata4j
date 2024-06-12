package org.fwx.proxy;

/**
 * 静态代理：模拟明星和经纪人的例子
 */
public class T01StaticProxy {
    public static void main(String[] args) {
        ProxyStar proxyStar = new ProxyStar(new Star());
        proxyStar.sign();
        // 调用经纪人唱歌，实则为明星唱歌
        proxyStar.sing();
        proxyStar.collectFee();
    }
}

/**
 * 明星接口
 */
interface StarInterface {
    // 签约
    public void sign();

    // 唱歌
    public void sing();

    // 收费
    public void collectFee();
}

/**
 * 明星实体类
 */
class Star implements StarInterface {

    @Override
    public void sign() {
        System.out.println("明星签约");
    }

    @Override
    public void sing() {
        System.out.println("明星唱歌");
    }

    @Override
    public void collectFee() {
        System.out.println("明星收钱");
    }
}

/**
 * 经纪人实体类
 */
class ProxyStar implements StarInterface{

    private StarInterface star;

    public ProxyStar(StarInterface star){
        this.star = star;
    }

    @Override
    public void sign() {
        System.out.println("经纪人代理签约");
    }

    @Override
    public void sing() {
        // 经纪人不会唱歌，只能明星唱歌
        star.sing();
    }

    @Override
    public void collectFee() {
        System.out.println("经纪人代理收钱");
    }
}