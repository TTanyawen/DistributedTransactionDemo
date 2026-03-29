package com.angela._3pc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BService {
    public boolean canCommit(String txId) {
        return true;
    }

    @Transactional
    public void preCommit(String txId) {
        //执行事务
    }

    @Transactional
    public void doCommit(String txId) {
        //提交事务
    }
    @Transactional
    public void abort(String txId) {
        //回滚事务
    }
}
