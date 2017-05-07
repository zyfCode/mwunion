package com.sungan.ad.dao.sys;

import com.sungan.ad.common.dao.UpdateByCondition;
import com.sungan.ad.dao.PlatAccountDAO;
import com.sungan.ad.dao.model.PlatAccount;

/**
 * Created by zhangyf18255 on 2017/5/6.
 */
public class PlatAccountWrapper {
    private PlatAccount platAccount;
    private PlatAccountDAO accountDAO;

    public PlatAccountWrapper(PlatAccount platAccount, PlatAccountDAO accountDAO) {
        this.platAccount = platAccount;
        this.accountDAO = accountDAO;
    }

    public int updatePlatAccount(){
        UpdateByCondition<PlatAccount> contidion = new UpdateByCondition<PlatAccount>(platAccount,true);
        int i = accountDAO.updateHql(contidion);
        return i;
    }

    public PlatAccount getPlatAccount() {
        return platAccount;
    }
}
