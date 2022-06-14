package org.fwx.lambda.annotation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * [ 自定义注解 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/14 11:40 ]
 */
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({TYPE,FIELD,METHOD,PARAMETER,CONSTRUCTOR,LOCAL_VARIABLE,ANNOTATION_TYPE,PACKAGE,TYPE_PARAMETER,TYPE_USE})
public @interface MyAnnotation {
    String value() default "fwx";
}
