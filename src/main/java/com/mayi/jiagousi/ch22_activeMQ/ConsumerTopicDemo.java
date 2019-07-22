package com.mayi.jiagousi.ch22_activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class ConsumerTopicDemo {
    public static void main(String[] args) throws JMSException {
        // 获取mq连接工厂
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
        // 创建连接
        Connection connection = connectionFactory.createConnection();
        // 启动连接
        connection.start();
        // 创建会话工厂
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // 创建队列路径
        Destination destination = session.createTopic("chen_topic");
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        while (true){
            // 监听消息
            TextMessage textMessage = (TextMessage) consumer.receive();
            if(textMessage != null){
                System.out.println("消费者获取到消息： text=" + textMessage.getText());
            }else{
                break;
            }
        }
        System.out.println("消费者消费消息完毕");
    }
}
