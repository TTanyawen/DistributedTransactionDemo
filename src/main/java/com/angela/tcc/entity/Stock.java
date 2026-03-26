package com.angela.tcc.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "t_stock")
public class Stock {

    @Id
    @Column(name = "product_id")
    private Long productId;
    private Integer total;
    private Integer frozen;

}
/*
CREATE TABLE t_stock (
         product_id BIGINT PRIMARY KEY,
         total INT,
         frozen INT
);
 */
