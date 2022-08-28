package com.jade.kafkamq.producer;

import com.alibaba.fastjson2.JSON;
import com.jade.kafkamq.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
@SuppressWarnings({"rawtypes","unchecked"})
public class Producer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    //发送消息方法
    public void send(int i) {
        Message message = new Message();
        message.setId("kafka_" + System.currentTimeMillis());
        message.setMsg(UUID.randomUUID().toString());
        message.setSendTime(new Date());
        kafkaTemplate.send("mars", JSON.toJSONString(message));
    }

}

