package com.angela.tcc.service;

import com.angela.tcc.dto.OrderDTO;
import com.angela.tcc.dto.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TccCoordinator {

    @Autowired
    private StockTccService stockService;

    @Autowired
    private OrderTccService orderService;

    public void createOrder(OrderDTO orderDTO, StockDTO stockDTO) {

        try {
            // 1. Try阶段
            boolean stockTry = stockService.prepare(stockDTO);
            boolean orderTry = orderService.prepare(orderDTO);

            if (!stockTry || !orderTry) {
                throw new RuntimeException("Try阶段失败");
            }

            // 2. Confirm阶段
            stockService.commit(stockDTO);
            orderService.commit(orderDTO);

        } catch (Exception e) {

            // 3. Cancel阶段（回滚）
            stockService.rollback(stockDTO);
            orderService.rollback(orderDTO);

            throw new RuntimeException("TCC事务失败");
        }
    }
}
