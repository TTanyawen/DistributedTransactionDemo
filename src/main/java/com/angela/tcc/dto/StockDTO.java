package com.angela.tcc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    // 商品ID
    private Long productId;

    // 扣减数量
    private Integer count;
}
