package org.fwx.lambda.exercise;

import org.fwx.lambda.Employee;
import org.fwx.lambda.data.FunctionInteface;
import org.fwx.lambda.data.FunctionInteface1;
import org.fwx.lambda.data.TestDatas;
import org.junit.Test;

import java.util.Collections;
import java.util.Locale;

/**
 * [ 关于 Lambda 的练习 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 9:33 ]
 */
public class LambdaExerciseTest {

    /**
     * 根据员工的年龄倒叙(顺序)排序，年龄相同的根据姓名自然排序
     */
    @Test
    public void test1(){
        Collections.sort(TestDatas.employeeList,(e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            } else {
                return - Integer.compare(e1.getAge(), e2.getAge());
            }
        });

        for (Employee employee : TestDatas.employeeList) {
            System.out.println(employee);
        }
    }

    /**
     * 调用自定义函数式接口方法
     * @param str
     * @param func
     */
    public void printValue(String str, FunctionInteface<String> func){
        func.printValue(str);
    }

    /**
     * Lambda 函数式接口处理字符串
     */
    @Test
    public void test2(){
        // 方式一
        FunctionInteface<String> func = (x) -> {
            System.out.println(x.toUpperCase());
        };

        func.printValue("fwx");

        // 方式二
        printValue("test", (x) -> System.out.println(x.toUpperCase()));
    }

    /**
     * Lambda 函数式接口，返回两个参数的处理结果
     */
    @Test
    public void test3(){
        FunctionInteface1<String, String>  func = (x, y) -> {
            String result = x + y;
            return result;
        };

        System.out.println(func.printValue("Hello ", "World !"));
    }
}
