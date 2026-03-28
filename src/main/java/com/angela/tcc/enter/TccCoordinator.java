package com.angela.tcc.enter;


import com.angela.tcc.order.service.OrderService2;
import com.angela.tcc.stock.service.StockService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TccCoordinator {

    @Resource
    private OrderService2 orderService2;

    @Resource
    private StockService stockService;
    //实际上是提供try confirm cancel就好，框架会处理，比方说Seata TODO
    //这里不是完整tcc的


    public void placeOrder(String txId, Long productId) {

        try {

            // TRY
            orderService2.tryCreate(txId, productId);
            stockService.tryFreeze(productId);

            // CONFIRM
            orderService2.confirm(txId);
            stockService.confirm(productId);

        } catch (Exception e) {

            // CANCEL
            orderService2.cancel(txId);
            stockService.cancel(productId);

            throw e;
        }
    }
}