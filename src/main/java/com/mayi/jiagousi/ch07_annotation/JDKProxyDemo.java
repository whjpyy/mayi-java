package com.mayi.jiagousi.ch07_annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

interface IPerson {
    void say(String word);
}

class Man implements IPerson{
    @Override
    public void say(String word) {
        System.out.println("man say " + word);
    }
}

public class JDKProxyDemo implements InvocationHandler {

    private Object target;
    public JDKProxyDemo(Object target){
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("调用方法前");
        method.invoke(target, args);
        System.out.println("调用方法后");
        return null;
    }

    public static void main(String[] args) {
        Man man = new Man();
        JDKProxyDemo jdkProxyDemo = new JDKProxyDemo(man);
        IPerson person = (IPerson) Proxy.newProxyInstance(man.getClass().getClassLoader(), man.getClass().getInterfaces(), jdkProxyDemo);
        person.say("hello");
    }
}
