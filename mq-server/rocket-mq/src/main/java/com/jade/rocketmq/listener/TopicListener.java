package com.jade.rocketmq.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "consumers", topic = "mars")
public class TopicListener implements RocketMQListener<String> {

    @Override
    public void onMessage(String msg) {
        System.out.println("处理消息:" + msg);
    }

}
