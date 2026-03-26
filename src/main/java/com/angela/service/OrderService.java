package com.angela.service;

import com.angela.mq.OrderTransactionProducer;
import com.angela.tcc.service.StockTccService;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    OrderTransactionProducer orderTransactionProducer;
    @Autowired
    StockTccService stockClient;

    /*
        1. 发送半消息
        2. listener会监听到，并且执行本地事务，成功commit，失败rollback
    */
    public void executeOrder() throws MQClientException {
        orderTransactionProducer.sendOrder("myOrder");
    }

    public void createOrder() {
        // 发送消息
        rocketMQTemplate.convertAndSend("order-topic", "创建订单");
    }
    public void createOrder(String content) {
        // 发送消息
//        rocketMQTemplate.convertAndSend("order-topic", "创建订单");
    }
}
