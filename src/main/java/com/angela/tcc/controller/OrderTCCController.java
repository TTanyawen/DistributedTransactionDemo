package com.angela.tcc.controller;

import com.angela.tcc.dto.OrderDTO;
import com.angela.tcc.dto.StockDTO;
import com.angela.tcc.service.TccCoordinator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order/tcc")
public class OrderTCCController {

    @Autowired
    private TccCoordinator coordinator;

    @PostMapping("/create")
    public String createOrder() {

        OrderDTO orderDTO = new OrderDTO(1L, 1001L,1001L, 2);
        StockDTO stockDTO = new StockDTO(1001L, 2);

        coordinator.createOrder(orderDTO, stockDTO);

        return "success";
    }
}
