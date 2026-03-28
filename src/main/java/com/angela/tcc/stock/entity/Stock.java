package com.angela.tcc.stock.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "t_stock")
@Data
public class Stock {

    @Id
    private Long productId;

    private Integer total;

    private Integer frozen;

    // getter setter
}
