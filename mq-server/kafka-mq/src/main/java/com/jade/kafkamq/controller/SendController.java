package com.jade.kafkamq.controller;

import com.jade.kafkamq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class SendController {

    @Autowired
    private Producer producer;

    @RequestMapping(value = "/send")
    public String send() {
        for (int i = 0; i < 10; i++) {
            producer.send(i);
        }
        return "{\"code\":0}";
    }
}
