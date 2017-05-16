package com.sungan.ad.service.plat.settle;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhangyf18255 on 2017/5/6.
 */
@Service
public class TransactionHelperImpl implements TransactionHelper {
    @Override
    @Transactional(value="transactionManager",propagation=Propagation.REQUIRES_NEW)
    public <T> T doInNewTran(HelperBiz<T> biz) {
        T t = biz.doNewTransaction();
        return t;
    }
}
