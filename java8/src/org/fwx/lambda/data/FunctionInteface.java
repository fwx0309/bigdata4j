package org.fwx.lambda.data;

/**
 * [ 一个输入，无返回值的函数式接口 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 9:58 ]
 */
@FunctionalInterface
public interface FunctionInteface<T> {
    /**
     * 打印处理结果
     */
    public void printValue(T t);
}
