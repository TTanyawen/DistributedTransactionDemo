package com.angela._3pc.ordin;

import com.angela._3pc.service.AService;
import com.angela._3pc.service.BService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class _3PCOrdinator {
    @Autowired
    private AService aService;

    @Autowired
    private BService bService;

    // 全局事务入口
    public void execute(String txId, Long productId) {

        try {
            //CanCommit
            boolean ok1 = aService.canCommit(productId);
            boolean ok2 = bService.canCommit(txId);

            if (!ok1 || !ok2) {
                abort(txId, productId);
                return;
            }

            //PreCommit
            aService.preCommit(productId);
            bService.preCommit(txId);

            //Commit
            aService.doCommit(productId);
            bService.doCommit(txId);

        } catch (Exception e) {
            abort(txId, productId);
            throw e;
        }
    }

    private void abort(String txId, Long productId) {
        aService.abort(productId);
        bService.abort(txId);
    }
}
