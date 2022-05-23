package org.fwx.lambda;

/**
 * [ Employee 过滤接口 ]
 *
 * @author : [fwx]
 * @version : [v1.0]
 * @createTime : [2022/5/17 10:01]
 */
public interface FilterData<T> {
    public boolean filter(T t);
}
