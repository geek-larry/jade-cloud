package com.jade.kafkamq.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Message {

    private String id;

    private String msg;

    private Date sendTime;

}
