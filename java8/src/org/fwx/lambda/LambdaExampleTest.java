package org.fwx.lambda;

import org.fwx.lambda.data.TestDatas;
import org.junit.Test;

import java.util.*;

/**
 * [ Lambda 测试类，用 lambda 表达式简化代码例子 ]
 *
 * @author : [fwx]
 * @version : [v1.0]
 * @createTime : [2022/5/8 18:11]
 */
public class LambdaExampleTest {

    /**
     * 匿名内部类方式
     */
    @Test
    public void test1(){
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * Lambda 表达式方式
     */
    @Test
    public void test2(){
        Comparator<Integer> comparator = (x,y) -> Integer.compare(x,y);

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    /**
     * 使用策略模式过滤集合，返回结果
     * @param list
     * @param fun
     * @return
     */
    public List<Employee> filterEmpFun(List<Employee> list,FilterData<Employee> fun){
        ArrayList<Employee> results = new ArrayList<>();
        // 过滤数据添加到返回的集合中
        for (Employee employee : list) {
            if(fun.filter(employee)){
                results.add(employee);
            }
        }
        return results;
    }

    /**
     * 策略模式按年龄过滤
     */
    @Test
    public void test3(){
        List<Employee> filterEmp = filterEmpFun(TestDatas.employeeList, new FilterDataByAge());
        for (Employee employee : filterEmp) {
            System.out.println(employee);
        }
    }

    /**
     * 策略模式按工资过滤
     */
    @Test
    public void test4(){
        List<Employee> filterEmp = filterEmpFun(TestDatas.employeeList, new FilterDataBySalary());
        for (Employee employee : filterEmp) {
            System.out.println(employee);
        }
    }

    /**
     * Lambda 优化策略模式按年龄过滤
     */
    @Test
    public void test5(){
        List<Employee> filterEmp = filterEmpFun(TestDatas.employeeList, (emp) -> emp.getAge() >= 30);
        for (Employee employee : filterEmp) {
            System.out.println(employee);
        }
    }

    /**
     * Lambda Stream 优化员工删选输出
     */
    @Test
    public void test6(){
        TestDatas.employeeList.stream()
                .filter((x) -> x.getAge() >30)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("------------------------");

        TestDatas.employeeList.stream()
                .filter((x) -> x.getAge() < 40)
                .map(Employee::getName)
                .forEach(System.out::println);
    }
}
