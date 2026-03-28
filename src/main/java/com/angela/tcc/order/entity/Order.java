package com.angela.tcc.order.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_order")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String txId;

    private Long productId;

    private String status; // TRY / CONFIRM / CANCEL

    // getter setter
}