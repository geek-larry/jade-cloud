package com.jade.rocketmq.controller;

import com.jade.rocketmq.sender.TestProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class TestController {
    @Autowired
    private TestProducer producer;

    @RequestMapping("/push")
    public String pushMsg(String msg) {
        try {
            return producer.send("flinksql", "push", msg);
        } catch (InterruptedException | RemotingException | MQClientException | MQBrokerException |
                 UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }
}
