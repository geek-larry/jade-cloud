package com.jade.rabbitmq.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.jade.common.core.domain.R;
import com.jade.rabbitmq.sender.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rabbit")
public class RabbitController {

    @Autowired
    private SimpleSender simpleSender;

    @Autowired
    private WorkSender workSender;

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private DirectSender directSender;

    @Autowired
    private TopicSender topicSender;

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    @ResponseBody
    public R<?> topicTest() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return R.ok(null);
    }

    @RequestMapping(value = "/direct", method = RequestMethod.GET)
    @ResponseBody
    public R<?> directTest() {
        for (int i = 0; i < 10; i++) {
            directSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return R.ok(null);
    }

    @RequestMapping(value = "/fanout", method = RequestMethod.GET)
    @ResponseBody
    public R<?> fanoutTest() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return R.ok(null);
    }

    @RequestMapping(value = "/work", method = RequestMethod.GET)
    @ResponseBody
    public R<?> workTest() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
            ThreadUtil.sleep(1000);
        }
        return R.ok(null);
    }

    @RequestMapping(value = "/simple", method = RequestMethod.GET)
    @ResponseBody
    public R<?> simpleTest() {
        for (int i = 0; i < 10; i++) {
            simpleSender.send();
            ThreadUtil.sleep(1000);
        }
        return R.ok(null);
    }
}
