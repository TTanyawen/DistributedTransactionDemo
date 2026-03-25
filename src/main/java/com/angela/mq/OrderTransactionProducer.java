package com.angela.mq;

import com.alibaba.fastjson.JSON;
import com.angela.service.TransactionExecuterImpl;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

// 1️⃣ 事务消息生产者
@Component
public class OrderTransactionProducer {

    @Autowired
    private TransactionMQProducer producer;
    @Autowired
    private TransactionExecuterImpl transactionExecuterImpl;



    public void sendOrder(String content) throws MQClientException {
        // 注册 TransactionListener
        producer.setTransactionListener(transactionExecuterImpl);
        Message msg = new Message("OrderTopic",
                "OrderTag",
                content,
                content.getBytes(StandardCharsets.UTF_8));

        // 发送事务消息
        producer.sendMessageInTransaction(msg, content);
    }
}
