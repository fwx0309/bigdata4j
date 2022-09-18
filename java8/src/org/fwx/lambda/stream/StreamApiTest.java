package org.fwx.lambda.stream;

import org.fwx.lambda.bean.Employee;
import org.fwx.lambda.data.TestDatas;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * [
 *  一、Stream API 的操作步骤：
 *  1. 创建 Stream
 *  2. 中间操作
 *  3. 终止操作(终端操作)
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 20:43 ]
 */
public class StreamApiTest {

    /**
     * 1.创建 Stream
     */
    @Test
    public void test1(){
        // 1.Collection 提供了两个方法  stream() 与 parallelStream()
        ArrayList<String> list = new ArrayList<>();
        // 获取一个顺序流
        Stream<String> stream = list.stream();
        // 获取一个并行流
        Stream<String> stringStream = list.parallelStream();

        // 2.通过 Arrays 中的 stream() 获取一个数组流
        Integer[] integers = new Integer[10];
        Stream<Integer> stream1 = Arrays.stream(integers);

        Character[] chars = new Character[10];
        Arrays.stream(chars);

        int[] intArr = new int[10];
        Arrays.stream(intArr);

        char[] charsArr = new char[10];
        // char[] 转换不了
        // Arrays.stream(charsArr);

        // 3.通过 Stream 类中静态方法 of()
        Stream<String> stream2 = Stream.of("aaa", "bbb", "ccc");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream3 = Stream.iterate(0, (d) -> d + 2);
        stream3.limit(10).forEach(System.out::println);

        System.out.println("-----------");

        Stream<Double> stream4 = Stream.generate(Math::random);
        stream4.limit(10).forEach(System.out::println);
    }

    /**
     * 2.中间操作
     * 	  筛选与切片
     * 		filter——接收 Lambda ， 从流中排除某些元素。
     * 		limit(n)——截断流，使其元素不超过给定数量。
     * 		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
     * 		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
     */
    @Test
    public void test2(){
        List<Employee> employeeList = TestDatas.employeeList;

        //内部迭代：迭代操作 Stream API 内部完成
        Stream<Employee> stream = employeeList.stream();
        //所有的中间操作不会做任何的处理
        stream
            .filter((d) -> {
                System.out.println("---execute---");
                return d.getAge() > 30;
            })
            .distinct()
            .limit(2)
            //只有当做终止操作时，所有的中间操作会一次性的全部执行，称为“惰性求值”
            .forEach(System.out::println);
    }


    /**
     * 2.中间操作
     * 	  映射
     * 		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * 		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */
    @Test
    public void test3(){
        List<String> list = TestDatas.list;

        list.stream()
                .map((d) -> d.toUpperCase())
                .forEach(System.out::println);

        // flapmap 需要把传进来的参数转成流返回
        // 总而言之，flatmap方法让你把一个流中的每个值都换成另一个流，然后把所有的流连接起来成为一个流。
        Stream<String> stringStream = list.stream()
                .flatMap((d) -> {
                    Stream<String> stream = Arrays.stream(d.split(""));
                    return stream;
                });
        stringStream.forEach(System.out::println);

    }

    /**
     * 2.中间操作
     * 	  排序
     * 		sorted()——自然排序
     * 		sorted(Comparator com)——自定制排序
     */
    @Test
    public void test4(){
        // ArrayList<String> 自然排序
        List<String> list = TestDatas.list;
        list.stream().sorted().forEach(System.out::println);

        // 员工排序
        List<Employee> employeeList = TestDatas.employeeList;
        employeeList.stream().sorted((a,b) -> {
            // 如果年龄相等按名字拍
            if(a.getAge() == b.getAge()){
                return a.getName().compareTo(b.getName());
            } else {
                return -Integer.compare(a.getAge(), b.getAge());
            }
        }).forEach(System.out::println);
    }

    /**
     * 3. 终止操作
     * 	    allMatch——检查是否匹配所有元素
     * 	    anyMatch——检查是否至少匹配一个元素
     * 		noneMatch——检查是否没有匹配的元素（一个都不匹配才为true）
     * 		findFirst——返回第一个元素
     * 		findAny——返回当前流中的任意元素
     * 		count——返回流中元素的总个数
     * 		max——返回流中最大值
     * 		min——返回流中最小值
     */
    @Test
    public void test5(){
        List<Employee> employeeList = TestDatas.employeeList;

        // allMatch——检查是否匹配所有元素
        boolean b = employeeList.stream().allMatch((d) -> d.getAge() > 30);
        System.out.println("b = " + b);

        // anyMatch——检查是否至少匹配一个元素
        boolean b1 = employeeList.stream().anyMatch((d) -> d.getAge() > 30);
        System.out.println("b1 = " + b1);

        // noneMatch——检查是否没有匹配的元素（一个都不匹配才为true）
        boolean b2 = employeeList.stream().noneMatch((d) -> d.getAge() > 300);
        System.out.println("b2 = " + b2);

        // findFirst——返回第一个元素
        Optional<Employee> employee = employeeList.stream().findFirst();
        System.out.println("employee = " + employee.get());

        // findAny——返回当前流中的任意元素
        // Optional<Employee> employee1 = employeeList.parallelStream().filter((d) -> d.getAge() == 28).findAny();
        Optional<Employee> employee1 = employeeList.parallelStream().findAny();
        System.out.println("employee1 = " + employee1.get());

        // count——返回流中元素的总个数
        System.out.println("employeeList.stream().count() = " + employeeList.stream().count());

        // max——返回流中最大值
        Optional<Integer> max = employeeList.stream().map(Employee::getAge).max(Integer::compare);
        System.out.println("max = " + max.get());

        // min——返回流中最小值
        Optional<Integer> min = employeeList.stream().map(Employee::getAge).min(Integer::compare);
        System.out.println("min = " + min.get());
    }

    /**
     * 3. 终止操作
     * 	    归约
     * 	    reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     */
    @Test
    public void test6(){

        // reduce(T identity, BinaryOperator)
        List<Integer> intList = TestDatas.intList;
        Integer reduce = intList.stream().reduce(0, (x, y) -> x + y);
        System.out.println("reduce = " + reduce);

        // reduce(BinaryOperator)
        Optional<Double> reduce1 = TestDatas.employeeList.stream().map((d) -> d.getSalary()).reduce((x, y) -> x + y);
        System.out.println("reduce1 = " + reduce1.get());
    }

    /**
     * 3. 终止操作
     * 	    收集
     * 	    collect——将流转换为其他形式。接收一个 Collector 接口的实现，用于给Stream中元素做汇总的方法
     */
    @Test
    public void test7(){
        List<Employee> employeeList = TestDatas.employeeList;

        // 收集到 List 中
        List<String> list = employeeList.stream().map((d) -> d.getName()).collect(Collectors.toList());
        //list.forEach(System.out::println);

        // 收集到 Set 中
        Set<String> set = employeeList.stream().map(Employee::getName).collect(Collectors.toSet());
        //set.forEach(System.out::println);

        // 收集到 LinkedList 中
        LinkedList<String> linkedList = employeeList.stream().map(Employee::getName).collect(Collectors.toCollection(LinkedList::new));
        //linkedList.forEach(System.out::println);

        // 获取总量
        Long collect = employeeList.stream().collect(Collectors.counting());
        //System.out.println("collect = " + collect);

        // 获取总和
        Double collect1 = employeeList.stream().collect(Collectors.summingDouble(Employee::getSalary));
        //System.out.println("collect1 = " + collect1);

        // 获取平均值
        Double collect3 = employeeList.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        //System.out.println("collect3 = " + collect3);

        // 获取最大值
        Optional<Employee> collect2 = employeeList.stream().collect(Collectors.maxBy((x, y) -> Double.compare(x.getSalary(), y.getSalary())));
        //System.out.println("collect2 = " + collect2.get());

        // 分组
        Map<Integer, List<Employee>> collect4 = employeeList.stream().collect(Collectors.groupingBy(Employee::getAge));
        //collect4.entrySet().forEach(System.out::println);

        // 多级分组
        Map<Integer,Map<Double,List<Employee>>> collect5 = employeeList.stream().collect(Collectors.groupingBy(Employee::getAge,Collectors.groupingBy((d) -> {
            return d.getSalary();
        })));
        /*collect5.entrySet().forEach((map) -> {
            if (map.getValue().size() == 1){
                System.out.println(map);
            } else {
                map.getValue().entrySet().forEach((d) -> {
                    System.out.println(map.getKey()  + "=" + d);
                });
            }
        });*/

        // 数据分区 根据条件进行判断后分区（true/false）
        Map<Boolean, List<Employee>> collect6 = employeeList.stream().collect(Collectors.partitioningBy((x) -> x.getSalary() > 3000));
        //collect6.entrySet().forEach(System.out::println);

        // 数据统计组合操作
        DoubleSummaryStatistics collect7 = employeeList.stream().collect(Collectors.summarizingDouble(Employee::getSalary));
        // System.out.println("collect7.getCount() = " + collect7.getCount());
        // System.out.println("collect7.getSum() = " + collect7.getSum());
        // System.out.println("collect7.getAverage() = " + collect7.getAverage());
        // System.out.println("collect7.getMax() = " + collect7.getMax());
        // System.out.println("collect7.getMin() = " + collect7.getMin());

        // join 把结果拼接起来
        // String collect8 = employeeList.stream().map(Employee::getName).collect(Collectors.joining(","));
        String collect8 = employeeList.stream().map(Employee::getName).collect(Collectors.joining(",","^","$"));
        //System.out.println("collect8 = " + collect8);

        // 练习：将拼音名按拆分的字母排序
        String reduce = TestDatas.employeeListEn.stream().map(Employee::getName).flatMap((name) -> {
            Stream<String> stream = Arrays.stream(name.split(""));
            return stream;
        }).sorted().reduce("", (x, y) -> x + y);
        System.out.println("reduce = " + reduce);
    }
}
