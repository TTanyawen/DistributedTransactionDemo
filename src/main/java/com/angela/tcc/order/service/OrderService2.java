package com.angela.tcc.order.service;


import com.angela.tcc.order.entity.Order;
import com.angela.tcc.order.repo.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class OrderService2 {

    @Resource
    private OrderRepository orderRepository;

    //实际上是提供try confirm cancel就好，框架会处理

    // TRY
    @Transactional
    public void tryCreate(String txId, Long productId) {

        Order exist = orderRepository.findByTxId(txId);

        // 幂等
        if (exist != null) {

            // 防悬挂：Cancel已发生
            if ("CANCEL".equals(exist.getStatus())) {
                throw new RuntimeException("已取消，禁止Try（防悬挂）");
            }

            return;
        }

        Order order = new Order();
        order.setTxId(txId);
        order.setProductId(productId);
        order.setStatus("TRY");

        orderRepository.save(order);
    }

    // CONFIRM
    @Transactional
    public void confirm(String txId) {

        Order order = orderRepository.findByTxId(txId);

        if (order == null || "CONFIRM".equals(order.getStatus())) {
            return;
        }

        if (!"TRY".equals(order.getStatus())) {
            return;
        }

        order.setStatus("CONFIRM");
        orderRepository.save(order);
    }

    // CANCEL
    @Transactional
    public void cancel(String txId) {

        Order order = orderRepository.findByTxId(txId);

        // 空回滚
        if (order == null) {

            Order cancel = new Order();
            cancel.setTxId(txId);
            cancel.setStatus("CANCEL");

            orderRepository.save(cancel);
            return;
        }

        if ("CANCEL".equals(order.getStatus())) {
            return;
        }

        if ("TRY".equals(order.getStatus())) {
            order.setStatus("CANCEL");
            orderRepository.save(order);
        }
    }
}
