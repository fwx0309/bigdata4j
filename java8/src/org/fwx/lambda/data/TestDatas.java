package org.fwx.lambda.data;

import org.fwx.lambda.Employee;

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
    // 测试的员工数据集合
    public static List<Employee> employeeList = Arrays.asList(
            new Employee("张三", 18, 2000.0D),
            new Employee("李四", 28, 3000.0D),
            new Employee("王五", 38, 4000.0D),
            new Employee("王五1", 38, 4001.0D),
            new Employee("王五2", 38, 4002.0D),
            new Employee("赵六", 48, 5000.0D),
            new Employee("田七", 58, 6000.0D)
    );
}
