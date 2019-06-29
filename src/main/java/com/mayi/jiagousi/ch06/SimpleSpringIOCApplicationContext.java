package com.mayi.jiagousi.ch06;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Field;
import java.util.List;

public class SimpleSpringIOCApplicationContext {

    private String xmlPath;
    public SimpleSpringIOCApplicationContext(String xmlPath){
        this.xmlPath = xmlPath;
    }

    public Object getBean(String beanId) throws DocumentException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        // 1.读取xml配置文件
        Document document = new SAXReader().read(this.getClass().getClassLoader().getResourceAsStream(xmlPath));
        Element rootEle = document.getRootElement();
        List<Element> elements = rootEle.elements();
        for(Element element : elements){
            String id = element.attributeValue("id");
            if(!id.equals(beanId)){
                continue;
            }
            String className = element.attributeValue("class");
            Class<?> forName = Class.forName(className);
            AnObject obj = (AnObject)forName.newInstance();
            List<Element> properties = element.elements();
            for(Element pro : properties){
                String name = pro.attributeValue("name");
                String value = pro.attributeValue("value");
                // 为属性赋值
                Field field = forName.getDeclaredField(name);
                field.setAccessible(true);
                if(field.getType() == int.class){
                    field.setInt(obj, Integer.parseInt(value));
                }else {
                    field.set(obj, value);
                }
            }
            return obj;
        }
        return null;
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, DocumentException, NoSuchFieldException {
        SimpleSpringIOCApplicationContext context = new SimpleSpringIOCApplicationContext("simple_spring.xml");
        AnObject obj1 = (AnObject) context.getBean("obj1");
        System.out.println(obj1);

    }
}
