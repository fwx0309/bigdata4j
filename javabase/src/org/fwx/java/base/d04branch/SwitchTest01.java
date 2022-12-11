package org.fwx.java.base.d04branch;

/**
 * [ Switch
 * 说明：
 * ① 根据switch表达式中的值，依次匹配各个case中的常量。一旦匹配成功，则进入相应case结构中，调用其执行语句。
 *   当调用完执行语句以后，则仍然继续向下执行其他case结构中的执行语句，直到遇到break关键字或此switch-case结构
 *   末尾结束为止。
 *
 * ② break,可以使用在switch-case结构中，表示一旦执行到此关键字，就跳出switch-case结构
 *
 * ③ switch结构中的表达式，只能是如下的6种数据类型之一：
 *    byte 、short、char、int、枚举类型(JDK5.0新增)、String类型(JDK7.0新增)
 *
 * ④ case 之后只能声明常量。不能声明范围。
 *
 * ⑤ break关键字是可选的。
 *
 * ⑥ default:相当于if-else结构中的else.
 *   default结构是可选的，而且位置是灵活的。
 * ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/10/5 7:46 ]
 */
public class SwitchTest01 {
    public static void main(String[] args) {
        int num = 1;
        switch (num){
            case 0:
                System.out.println("zero");
                break;
            case 1:
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
            default:
                System.out.println("error!");
        }
    }
}
