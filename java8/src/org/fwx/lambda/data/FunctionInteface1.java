package org.fwx.lambda.data;

/**
 * [ 两个输入类型T，一个返回值类型R，函数式接口 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/5/25 9:58 ]
 */
@FunctionalInterface
public interface FunctionInteface1<T,R> {
    /**
     * 返回两个数据的处理结果
     * @param t1
     * @param t2
     * @return
     */
    public R printValue(T t1,T t2);
}
