package com.mayi.jiagousi.ch18_spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class BeanLifeCycleDemo implements BeanNameAware, BeanFactoryAware, ApplicationContextAware
    , BeanPostProcessor, DisposableBean {

    public BeanLifeCycleDemo(){
        System.out.println("1.无参构造函数");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("2.setBeanName: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("3.setBeanFactory: " + beanFactory);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("4.setApplicationContext: " + applicationContext);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName : " + beanName + "初始化开始之前");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("beanName : " + beanName + "初始化开始之后");
        return bean;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁bean");
    }
}
