package com.mayi.springbootrocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Consumer {

    private static Map<String, Object> logMap = new HashMap<>();

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("rmq-group");
        consumer.setNamesrvAddr("192.168.31.111:9876;192.168.31.112:9876");
        consumer.setInstanceName("consumer");
        consumer.setVipChannelEnabled(false);
        consumer.subscribe("chen-topic", "TagA");

        consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
            String keys = null;
            String msgId = null;
            for (MessageExt msg : msgs) {
                try {
                    keys = msg.getKeys();
                    msgId = msg.getMsgId();
                    if (logMap.containsKey(keys)) {
                        System.out.println("key: " + keys + ", msgId： " + msgId + "----已经重复消费");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    System.out.println("key: " + keys + ", msgId： " + msgId + "----" + new String(msg.getBody()));
                } finally {
                    logMap.put(keys, msgId);
                }
            }
            System.out.println("开始报错了。。");
            try {
                int i = 1 / 0;
            } catch (Exception e) {
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
        System.out.println("Consumer Started.");
    }
}
