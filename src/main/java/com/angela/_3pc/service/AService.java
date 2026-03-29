package com.angela._3pc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AService {
    public boolean canCommit(Long productId) {
        return true;
    }


    @Transactional
    public void preCommit(Long productId) {
        //执行事务
    }

    @Transactional
    public void doCommit(Long productId) {
        //提交事务
    }

    @Transactional
    public void abort(Long productId) {
        //回滚事务
    }
}
