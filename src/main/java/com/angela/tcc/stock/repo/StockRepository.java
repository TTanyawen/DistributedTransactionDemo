package com.angela.tcc.stock.repo;


import com.angela.tcc.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface StockRepository extends JpaRepository<Stock, Long> {

    // TRY：冻结库存
    @Modifying
    @Query("update Stock s set s.frozen = s.frozen + 1 " +
            "where s.productId = ?1 and (s.total - s.frozen) > 0")
    int freeze(Long productId);

    // CONFIRM：真正扣减
    @Modifying
    @Query("update Stock s set s.total = s.total - 1, s.frozen = s.frozen - 1 " +
            "where s.productId = ?1 and s.frozen > 0")
    int confirm(Long productId);

    // CANCEL：释放冻结
    @Modifying
    @Query("update Stock s set s.frozen = s.frozen - 1 " +
            "where s.productId = ?1 and s.frozen > 0")
    int unfreeze(Long productId);
}