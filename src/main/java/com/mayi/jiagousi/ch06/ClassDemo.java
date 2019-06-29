package com.mayi.jiagousi.ch06;

import lombok.Data;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Data
class AnObject{
    private int id;
    private String name;

    public AnObject() {
        System.out.println("无参构造函数-使用java反射创建User");
    }

    public AnObject(int id, String name){
        System.out.println("有参构造函数-使用java反射创建User[id="+id+",name="+name+"]");
        this.id = id;
        this.name = name;
    }
}

public class ClassDemo {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // new创建对象
        FastUser fastUser = new FastUser();
        // 使用反射机制创建对象
        final Class<?> forName = Class.forName("com.mayi.jiagousi.ch06.AnObject");
        final AnObject classUser = (AnObject)forName.newInstance();
        classUser.setId(22);
        System.out.println(classUser);
        System.out.println("-----------------");
        final Constructor<?> constructor = forName.getConstructor(int.class, String.class);
        final AnObject obj2 = (AnObject) constructor.newInstance(12, "cyz");
        System.out.println(obj2);
    }
}
