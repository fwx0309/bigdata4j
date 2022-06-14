package org.fwx.lambda.annotation;

import org.junit.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;

/**
 * [ 重复注释和类型注释 ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/6/14 13:50 ]
 */
public class MyAnnotationTest {

    @MyAnnotation("hello")
    @MyAnnotation("word")
    public void show(@MyAnnotation("args") String args){}

    @Test
    public void test() throws NoSuchMethodException {
        Class<MyAnnotationTest> testClass = MyAnnotationTest.class;
        Method show = testClass.getMethod("show",String.class);

        // 获取方法注解
        MyAnnotation[] annotationsByType = show.getAnnotationsByType(MyAnnotation.class);
        for (MyAnnotation myAnnotation : annotationsByType) {
            System.out.println("myAnnotation = " + myAnnotation.value());
        }

        // 获取参数注解
        AnnotatedType[] parameterTypes = show.getAnnotatedParameterTypes();
        for (AnnotatedType parameterType : parameterTypes) {
            System.out.println("parameterType = " + parameterType.getAnnotation(MyAnnotation.class).value());
        }

    }
}
