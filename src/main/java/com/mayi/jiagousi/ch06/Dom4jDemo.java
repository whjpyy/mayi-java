package com.mayi.jiagousi.ch06;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.Iterator;
import java.util.List;

public class Dom4jDemo {

    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read("src\\main\\resources\\students.xml");
        Element root = document.getRootElement();
        getNodes(root);
    }

    public static void getNodes(Element ele) {
        // 获取节点名称和节点的值
        System.out.println("节点：" + ele.getName() + "=" + ele.getTextTrim());
        // 获取节点的所有属性
        List<Attribute> attrs =  ele.attributes();
        for (Attribute attr : attrs){
            // 打印属性名和属性值
            System.out.println("属性：" + attr.getName() + "=" + attr.getValue());
        }
        Iterator iterator = ele.elementIterator();
        while (iterator.hasNext()){
            getNodes((Element) iterator.next());
        }
    }
}
