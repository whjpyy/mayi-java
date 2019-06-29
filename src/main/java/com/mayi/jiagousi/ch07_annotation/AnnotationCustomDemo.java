package com.mayi.jiagousi.ch07_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation{
    int beanId() default 0;
    String className() default "";
    String[] arr();
}

@TestAnnotation(beanId = 1, className = "com.chen", arr = {"a", "b"})
public class AnnotationCustomDemo {
    public void add(){

    }
}
