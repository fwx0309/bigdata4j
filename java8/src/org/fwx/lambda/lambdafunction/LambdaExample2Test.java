package org.fwx.lambda.lambdafunction;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * [
 *  Java8 内置的四大核心函数式接口
 *  其它内置内置函数接口在 java.util.function 下
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 14:24 ]
 */
public class LambdaExample2Test {

    /**
     * Consumer<T> : 消费型接口
     *  void accept(T t);
     */
    @Test
    public void test1(){
        Consumer<String> consumer = (d) -> System.out.println(d);
        consumer.accept("Lambda !!!");
    }

    /**
     * Supplier<T> : 供给型接口
     *  T get();
     */
    @Test
    public void test2() {
        List<Integer> numList = getNumList(10, () -> (int) (Math.random() * 100));
        for (Integer num : numList) {
            System.out.println(num);
        }
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer integer = sup.get();
            list.add(integer);
        }
        return list;
    }

    /**
     * Function<T, R> : 函数型接口
     *  R apply(T t);
     */
    @Test
    public void test3() {
        Function<String, String> fun = (d) -> d.toUpperCase();
        System.out.println(fun.apply("lambda !!!"));
    }

    /**
     * Predicate<T> : 断言型接口
     *  boolean test(T t);
     */
    @Test
    public void test4() {
        String text = "Apache Spark is a multi-language engine for executing data engineering, data science," +
                " and machine learning on single-node machines or clusters.";
        String[] stringArr = text.split(" ");

        // 判断函数: 字符串长度大于3
        Predicate<String> predicate = (d) -> d.length() > 3;

        List<String> list = new ArrayList<>();
        for (String s : stringArr) {
            // 过滤判断
            if (predicate.test(s)){
                list.add(s);
            }
        }

        // 过滤后的数据
        for (String s : list) {
            System.out.println(s);
        }
    }
}
