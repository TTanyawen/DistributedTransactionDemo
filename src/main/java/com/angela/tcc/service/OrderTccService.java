package com.angela.tcc.service;

import com.angela.tcc.TccAction;
import com.angela.tcc.dto.OrderDTO;
import com.angela.tcc.entity.Order;
import com.angela.tcc.repo.OrderRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderTccService implements TccAction<OrderDTO> {

    @Autowired
    private OrderRepo orderRepo;

    /**
     * Try：创建订单（pending状态）
     */
    @Transactional
    @Override
    public boolean prepare(OrderDTO dto) {
        log.info("Try：创建订单");

        Order order=new Order();
        order.setUserId(dto.getUserId());
        order.setProductId(dto.getProductId());
        order.setCount(dto.getCount());
        order.setStatus("TRY");
        orderRepo.save(order);
        log.info("Try:创建订单 finish");
        return true;
    }

    /**
     * Confirm：订单完成
     */
    @Transactional
    @Override
    public boolean commit(OrderDTO dto) {
        orderRepo.updateStatus(dto.getOrderId(), "CONFIRM");
        return true;
    }

    /**
     * Cancel：订单取消
     */
    @Transactional
    @Override
    public boolean rollback(OrderDTO dto) {
        orderRepo.updateStatus(dto.getOrderId(), "CANCEL");
        return true;
    }
}
