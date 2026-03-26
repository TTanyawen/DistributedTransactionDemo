package com.angela.tcc.repo;

import com.angela.tcc.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockRepo extends JpaRepository<Stock, Long> {
    @Modifying
    @Query(value=
            "update t_stock SET frozen = frozen + ?2 WHERE product_id = ?1 AND total - frozen >= ?2"
            ,nativeQuery = true)
    int freezeStock(Long productId, Integer count);

    @Modifying
    @Query(value=
            "update t_stock SET total = total - ?2, frozen = frozen - ?2 WHERE product_id = ?1"
            ,nativeQuery = true)
    void deductStock(Long productId, Integer count);

    @Modifying
    @Query(value=
            "update t_stock SET frozen = frozen - ?2 WHERE product_id = ?1"
            ,nativeQuery = true)
    void unfreezeStock(Long productId, Integer count);
}
