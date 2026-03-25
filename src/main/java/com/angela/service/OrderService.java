package com.angela.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void createOrder() {
        // 发送消息
        rocketMQTemplate.convertAndSend("order-topic", "创建订单");
    }
}
