package com.angela.tcc.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "product_id")
    private Long productId;

    private Integer count;
    @Column(name = "status")
    private String status;
}
/*
CREATE TABLE t_order (
         id BIGINT PRIMARY KEY AUTO_INCREMENT,
         user_id BIGINT,
         product_id BIGINT,
         count INT,
         status VARCHAR(20) -- TRY / CONFIRM / CANCEL
);
 */