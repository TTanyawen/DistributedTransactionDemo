package com.angela.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketMQProducerConfig {

    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Bean
    public TransactionMQProducer transactionMQProducer() throws MQClientException {
        // 事务消息生产者
        TransactionMQProducer producer = new TransactionMQProducer("order-transaction-group");

        producer.setNamesrvAddr(nameServer);

        // 可选：设置线程池大小、消息发送超时时间等
        producer.setSendMsgTimeout(5000);

        // 必须启动
        producer.start();

        return producer;
    }
}
