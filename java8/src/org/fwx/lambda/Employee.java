package org.fwx.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * [ 测试用户类 ]
 *
 * @author : [fwx]
 * @version : [v1.0]
 * @createTime : [2022/5/17 9:53]
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double salary;
}
