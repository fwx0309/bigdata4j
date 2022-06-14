package org.fwx.lambda.data;

import org.fwx.lambda.bean.Employee;

import java.util.Arrays;
import java.util.List;

/**
 * [ 测试数据 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 9:35 ]
 */
public class TestDatas {

    /**
     * 测试的员工数据集合
     */
    public static List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 2000.0D),
            new Employee("李四", 28, 3000.0D),
            new Employee("王五", 38, 4000.0D),
            new Employee("王五", 38, 4001.0D),
            new Employee("王五1", 38, 4003.0D),
            new Employee("王五1", 38, 4001.0D),
            new Employee("王五2", 38, 4002.0D),
            new Employee("赵六", 48, 5000.0D),
            new Employee("田七", 58, 6000.0D)
    );

    public static List<Employee> employeeListEn = Arrays.asList(
            new Employee("zhangsan", 18, 2000.0D),
            new Employee("lisi", 28, 3000.0D),
            new Employee("wangwu", 38, 4000.0D),
            new Employee("zhaoliu", 38, 4001.0D),
            new Employee("tianqi", 38, 4001.0D)
    );

    /**
     * 测试 string list
     */
    public static List<String> list = Arrays.asList("ddd", "aaa", "bbb", "ccc");

    /**
     * 测试 int list
     */
    public static List<Integer> intList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
}
