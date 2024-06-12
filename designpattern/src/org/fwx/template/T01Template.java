package org.fwx.template;

/**
 * 模板设计模式
 */
public class T01Template {
    public static void main(String[] args) {
        TemplateF t = new TemplateS();
        t.process();
    }
}

/**
 * 模板父类
 */
abstract class TemplateF {
    /**
     * 执行任务
     */
    public void process() {
        long startTime = System.currentTimeMillis();

        code();

        long endTime = System.currentTimeMillis();
        System.out.println("程序执行用时：" + (endTime - startTime) + "ms");
    }

    /**
     * 功能方法体
     */
    public abstract void code();
}

/**
 * 模板子类
 */
class TemplateS extends TemplateF {

    /**
     * 功能方法体
     */
    @Override
    public void code() {
        try {
            Thread.sleep(2000);
            System.out.println("子类实际的方法体执行");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
