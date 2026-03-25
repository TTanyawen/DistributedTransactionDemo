package com.angela.service;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class TransactionExecuterImpl implements TransactionListener {

//    @Autowired
//    private OrderService orderService; // 本地事务服务

    // 执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String content=(String)arg;
        try {
//            orderService.createOrder(content); // 本地事务逻辑xxx
            System.out.println("执行本地事务成功");
            return LocalTransactionState.COMMIT_MESSAGE;
        } catch (Exception e) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        }
    }

    /*回查
     *当没有收到commit/rollback，根据查询结果来判断是否提交mq
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String orderId = new String(msg.getBody(), StandardCharsets.UTF_8);
        boolean exist = true;//这里做检查
        return exist ? LocalTransactionState.COMMIT_MESSAGE
                : LocalTransactionState.ROLLBACK_MESSAGE;
    }
}
