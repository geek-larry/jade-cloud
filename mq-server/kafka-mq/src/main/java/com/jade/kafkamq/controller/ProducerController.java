package com.jade.kafkamq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    // Kafka 模板用来向 kafka 发送数据
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping("/produce")
    public String data(String msg) {
        kafkaTemplate.send("first", msg);
        return "ok";
    }
}
