package com.angela.tcc.order.controller;


import com.angela.tcc.order.service.OrderService2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController2 {

    @Resource
    private OrderService2 orderService2;

    //http://localhost:8087/order/tcc/create?productId=1
    @PostMapping("/create")
    public String create(@RequestParam Long productId) {

        String txId = UUID.randomUUID().toString();

        orderService2.tryCreate(txId, productId);

        return txId;
    }
}
