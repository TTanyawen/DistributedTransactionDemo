package com.angela.controller;

import com.angela.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    @Autowired
    OrderService orderService;
    @RequestMapping("/order")
    public String order() {
        orderService.createOrder();
        return "order success";
    }
}
