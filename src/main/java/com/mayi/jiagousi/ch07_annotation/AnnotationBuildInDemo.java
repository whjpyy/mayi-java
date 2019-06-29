package com.mayi.jiagousi.ch07_annotation;

/**
 * 常用的内置注解
 */
public class AnnotationBuildInDemo {
    @Override
    public String toString() {
        return super.toString();
    }

    @SuppressWarnings({"deprecation"})
    public void add(){
        add2();
    }

    @Deprecated
    public void add2(){

    }
}
