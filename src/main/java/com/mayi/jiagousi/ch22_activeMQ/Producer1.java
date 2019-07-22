package com.mayi.jiagousi.ch22_activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer1 {

    public static void main(String[] args) throws JMSException {
        // 获取mq连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话工厂
        Session session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
        // 创建队列路径
        Destination destination = session.createQueue("chen_queue");
        // 创建生产者
        MessageProducer producer = session.createProducer(destination);
        // 不持久化
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        for (int i = 0; i < 5; i++) {
            sendMsg(session, producer, "我是生产者" + i);
        }
    }

    public static void sendMsg(Session session, MessageProducer producer, String msg) throws JMSException {
        TextMessage textMessage = session.createTextMessage("hello activemq: " + msg);
        producer.send(textMessage);
    }
}
