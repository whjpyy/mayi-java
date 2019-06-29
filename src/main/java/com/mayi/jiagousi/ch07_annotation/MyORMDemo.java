package com.mayi.jiagousi.ch07_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Property{
    String name();
    int length() default 0;
}

@Table("student")
class Student{
    @Property(name = "id")
    private int id;
    @Property(name = "name")
    private String name;
    @Property(name = "age")
    private String age;
}

public class MyORMDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> forName = Class.forName("com.mayi.jiagousi.ch07_annotation.Student");
        // 获取到类上的注解
        Table aTable = forName.getDeclaredAnnotation(Table.class);
        Field[] fields = forName.getDeclaredFields();
        StringBuffer sb = new StringBuffer("select ");
        for (Field field : fields){
            Property aProperty = field.getDeclaredAnnotation(Property.class);
            sb.append(aProperty.name() + ", ");
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" from " + aTable.value());
        System.out.println(sb.toString());
    }
}
