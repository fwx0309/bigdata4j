package org.fwx.lambda;

/**
 * [ 根据工资过滤 Emp ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/17 11:31 ]
 */
public class FilterDataBySalary implements FilterData<Employee> {
    @Override
    public boolean filter(Employee employee) {
        return employee.getSalary() >= 3000;
    }
}
