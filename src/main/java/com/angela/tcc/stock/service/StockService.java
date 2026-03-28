package com.angela.tcc.stock.service;


import com.angela.tcc.stock.repo.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class StockService {

    @Resource
    private StockRepository stockRepository;

    @Transactional
    public void tryFreeze(Long productId) {
        int res = stockRepository.freeze(productId);
        if (res == 0) {
            throw new RuntimeException("库存不足");
        }
    }

    @Transactional
    public void confirm(Long productId) {
        stockRepository.confirm(productId);
    }

    @Transactional
    public void cancel(Long productId) {
        stockRepository.unfreeze(productId);
    }
}
