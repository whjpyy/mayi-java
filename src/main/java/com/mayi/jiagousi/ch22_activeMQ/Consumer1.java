package com.mayi.jiagousi.ch22_activeMQ;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Consumer1 {
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
        // 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);
        while (true){
            // 监听消息
            TextMessage textMessage = (TextMessage) consumer.receive();
            if(textMessage != null){
                System.out.println("消费者获取到消息： text=" + textMessage.getText());
                // 手动签收消息
                textMessage.acknowledge();
            }else{
                break;
            }
        }
        System.out.println("消费者消费消息完毕");
    }
}
