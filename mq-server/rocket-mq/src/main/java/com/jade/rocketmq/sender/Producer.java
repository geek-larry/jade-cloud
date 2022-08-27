package com.jade.rocketmq.sender;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer {
    public static void main(String[] args) throws Exception {
        //创建一个生产者，指定生产者组为sanyouProducer
        DefaultMQProducer producer = new DefaultMQProducer("sanyouProducer");

        // 指定NameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");

        producer.setSendMsgTimeout(60000);

        // 启动生产者
        producer.start();

        Message msg = new Message("mars", "TagA", "hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));

        // 发送消息并得到消息的发送结果，然后打印
        SendResult sendResult = producer.send(msg);
        System.out.printf("%s%n", sendResult);

        // 关闭生产者
        producer.shutdown();
    }

}
