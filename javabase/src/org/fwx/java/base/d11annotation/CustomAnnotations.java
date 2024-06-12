package org.fwx.java.base.d11annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;

/**
 * @ClassName CustomAnnotations
 * @Description 用于 CustomAnnotation 的 重复注解
 * @Author Fwx
 * @Date 2023/3/27 16:55
 * @Version 1.0
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
// 生命周期
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotations {
    CustomAnnotation[] value();
}
