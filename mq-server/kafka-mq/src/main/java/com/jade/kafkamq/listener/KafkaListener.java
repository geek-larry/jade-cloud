package com.jade.kafkamq.listener;

import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaListener {
    // 指定要监听的 topic
    @org.springframework.kafka.annotation.KafkaListener(topics = "first")
    public void consumeTopic(String msg) { // 参数: 收到的 value
        System.out.println("收到的信息: " + msg);
    }
}
