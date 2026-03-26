package com.angela.tcc.repo;

import com.angela.tcc.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface OrderRepo extends JpaRepository<Order, Long> {
    /**
     * 创建订单（Try阶段）
     */
//    @Modifying
//    @Transactional
//    @Query(value = "INSERT INTO t_order(user_id, product_id, count, status) " +
//            "VALUES (:userId, :productId, :count, :status)", nativeQuery = true)
//    void createOrder(@Param("userId") Long userId,
//                     @Param("productId") Long productId,
//                     @Param("count") Integer count,
//                     @Param("status") String status);


    /**
     * 更新订单状态（Confirm / Cancel）
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE t_order o SET o.status = ?2 WHERE o.id = ?1"
            ,nativeQuery = true)
    int updateStatus(@Param("orderId") Long orderId,
                     @Param("status") String status);
}
