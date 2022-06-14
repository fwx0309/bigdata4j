package org.fwx.lambda;

import org.fwx.lambda.bean.Employee;

/**
 * [ 根据年龄过滤 Emp ]
 *
 * @author : [fwx]
 * @version : [v1.0]
 * @createTime : [2022/5/17 10:05]
 */
public class FilterDataByAge implements FilterData<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getAge() >30;
    }
}
