package org.fwx.java.base.d11annotation;

import java.lang.annotation.Annotation;
import java.util.ArrayList;

/**
 * @ClassName CustomAnnotationTest
 * @Description TODO
 * @Author Fwx
 * @Date 2023/3/27 15:35
 * @Version 1.0
 */

// jdk8 之前的写法
//@CustomAnnotations({@CustomAnnotation(value = "hi"),@CustomAnnotation(value = "abc")})
// jdk8 之后的写法 （需要在 CustomAnnotation注解中添加 @Repeatable(CustomAnnotations.class) ）
@CustomAnnotation(value = "hi")
@CustomAnnotation(value = "abc")
public class CustomAnnotationTest {
    public static void main(String[] args) {
        Class<CustomAnnotationTest> aClass = CustomAnnotationTest.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println("annotation = " + annotation);
        }
    }
}

// jdk8 之后扩展的注解功能 TYPE_PARAMETER,TYPE_USE
class Anno<@CustomAnnotation Y>{
    public void test(long s){
        ArrayList<@CustomAnnotation String> list = new ArrayList<>();
        int s1 = (@CustomAnnotation int) s;
    }
}
