package com.mayi.jiagousi.ch07_annotation;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyDemo implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CGLIB调用方法前");
        methodProxy.invokeSuper(o, objects);
        System.out.println("CGLIB调用方法后");
        return null;
    }

    public static void main(String[] args) {
        Man man = new Man();
        // 通过CGLIB动态代理获取代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(man.getClass());
        enhancer.setCallback(new CglibProxyDemo());
        IPerson proxy = (IPerson) enhancer.create();
        proxy.say("fuck...");
    }
}
