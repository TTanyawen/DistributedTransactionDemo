package com.angela.tcc.service;

import com.angela.tcc.dto.StockDTO;
import com.angela.tcc.TccAction;
import com.angela.tcc.repo.StockRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StockTccService implements TccAction<StockDTO> {

    @Autowired
    private StockRepo stockRepo;

    /**
     * Try：冻结库存
     */
    @Transactional
    @Override
    public boolean prepare(StockDTO dto) {
        log.info("Try：冻结库存");
        int updated = stockRepo.freezeStock(dto.getProductId(), dto.getCount());
        log.info("Try：冻结库存 finish");
        return updated > 0;
    }

    /**
     * Confirm：真正扣减库存
     */
    @Transactional
    @Override
    public boolean commit(StockDTO dto) {
        log.info("Confirm：真正扣减库存");
        stockRepo.deductStock(dto.getProductId(), dto.getCount());
        log.info("Confirm：真正扣减库存 finish");
        return true;
    }

    /**
     * Cancel：释放冻结库存
     */
    @Transactional
    @Override
    public boolean rollback(StockDTO dto) {
        stockRepo.unfreezeStock(dto.getProductId(), dto.getCount());
        return true;
    }
}
