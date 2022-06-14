package org.fwx.lambda.lambdafunction;

import org.fwx.lambda.bean.Employee;
import org.fwx.lambda.data.TestDatas;
import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * [
 *  一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 *  			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 *      1. 对象的引用 :: 实例方法名
 *      2. 类名 :: 静态方法名
 *      3. 类名 :: 实例方法名
 *
 *  注意：
 *  	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 *  	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 *  二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *      1. 类名 :: new
 *
 *  三、数组引用
 *      类型[] :: new;
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 16:35 ]
 */
public class LambdaExample3Test {

    /**
     * 方法引用
     * 对象的引用 :: 实例方法名
     */
    @Test
    public void test1(){
        // PrintStream printStream = System.out;
        // printStream.println("test");
        Consumer<String> consumer = (d) -> System.out.println(d);
        consumer.accept("old");

        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("new");
    }

    /**
     * 方法引用
     * 类名 :: 静态方法名
     */
    @Test
    public void test2(){
        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator.compare(1,2));
    }

    /**
     * 方法引用
     * 类名 :: 实例方法名
     */
    @Test
    public void test3(){
        Employee employee = TestDatas.employeeList.get(0);
        Supplier<String> supplier = employee::getName;
        System.out.println(supplier.get());
    }

    /**
     * 构造器引用
     * 类名 :: new
     */
    @Test
    public void test4(){
        // 无参构造
        Supplier<Employee> supplier = Employee::new;
        System.out.println(supplier.get());

        // 两个参数的构造
        BiFunction<String, Integer, Employee> biFunction = Employee::new;
        System.out.println(biFunction.apply("zhangsan", 18));
    }

    /**
     * 数组引用
     * 类型[] :: new;
     */
    @Test
    public void test5(){
        Function<Integer,String[]> function = String[]::new;
        System.out.println(function.apply(10).length);
    }
}
