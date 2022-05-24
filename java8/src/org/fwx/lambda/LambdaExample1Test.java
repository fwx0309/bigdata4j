package org.fwx.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * [
 *  一、Lambda 表达式的基础语法：Java8中引入了一个新的操作符 "->" 该操作符称为箭头操作符或 Lambda 操作符箭头操作符将 Lambda 表达式拆分成两部分：
 *  左侧：Lambda 表达式的参数列表
 *  右侧：Lambda 表达式中所需执行的功能， 即 Lambda 体
 *
 *  二、Lambda 表达式需要“函数式接口”的支持
 *  函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。 可以使用注解 @FunctionalInterface 修饰可以检查是否是函数式接口
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/24 17:36 ]
 */
public class LambdaExample1Test {
    /**
     * 语法格式一：无参数，无返回值
     *  () -> System.out.println("Hello Lambda!");
     */
    @Test
    public void test1(){
        // 内部类调用方法局部变量，jdk1.7前，必须带上final
        int num = 100;

        // 匿名内部类方式实现
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("inner class !" + num);
            }
        };
        runnable.run();

        // Lambda 方式实现
        Runnable runnable1 = () -> System.out.println("lambda fun !" + num);
        runnable1.run();
    }

    /**
     * 语法格式二：有一个参数，并且无返回值
     *  (x) -> System.out.println(x);
     */
    @Test
    public void test2(){
        Consumer<Integer> consumer = (d) -> System.out.println(d);
        consumer.accept(10);
    }

    /**
     * 语法格式三：若只有一个参数，小括号可以省略不写
     *  x -> System.out.println(x)
     */
    @Test
    public void test3(){
        Consumer<Integer> consumer = d -> System.out.println(d);
        consumer.accept(10);
    }

    /**
     * 语法格式四：有两个以上的参数，有返回值，并且 Lambda 体中有多条语句
     *  Comparator<Integer> com = (x, y) -> {
     *  	System.out.println("函数式接口");
     *  	return Integer.compare(x, y);
     *  };
     */
    @Test
    public void test4(){
        Comparator<Integer> comparator = ((o1, o2) -> {
            System.out.println("o1 = " + o1);
            System.out.println("o2 = " + o2);
            return Integer.compare(o1,o2);
        });

        System.out.println(comparator.compare(10, 20));
    }

    /**
     * 语法格式五：若 Lambda 体中只有一条语句， return 和 大括号都可以省略不写
     *  Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
     * 语法格式六：Lambda 表达式的参数列表的数据类型可以省略不写，因为JVM编译器通过上下文推断出，数据类型，即“类型推断”
     *  (Integer x, Integer y) -> Integer.compare(x, y);
     */
    @Test
    public void test5(){
        Comparator<Integer> comparator = ((o1, o2) -> Integer.compare(o1,o2));
        System.out.println(comparator.compare(3, 2));
    }
}
