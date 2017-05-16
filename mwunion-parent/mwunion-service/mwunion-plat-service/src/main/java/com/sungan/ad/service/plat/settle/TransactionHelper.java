package com.sungan.ad.service.plat.settle;

import com.sungan.ad.commons.MuService;

/**
 * Created by zhangyf18255 on 2017/5/6.
 */
public interface TransactionHelper {

    <T> T doInNewTran(HelperBiz<T> biz);
}
