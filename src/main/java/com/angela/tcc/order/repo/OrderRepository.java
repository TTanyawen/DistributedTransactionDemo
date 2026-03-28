package com.angela.tcc.order.repo;


import com.angela.tcc.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByTxId(String txId);
}