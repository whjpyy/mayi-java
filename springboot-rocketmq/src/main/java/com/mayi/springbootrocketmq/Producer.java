package com.mayi.springbootrocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

public class Producer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setNamesrvAddr("192.168.31.111:9876;192.168.31.112:9876");
        producer.setInstanceName("producer");
        producer.setVipChannelEnabled(false);
        producer.start();
        try{
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);
                Message msg = new Message("chen-topic", "TagA", ("chen-64" + i).getBytes());
                msg.setKeys(System.currentTimeMillis() + "");
                SendResult result = producer.send(msg);
                System.out.println(result);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        }
    }
}
