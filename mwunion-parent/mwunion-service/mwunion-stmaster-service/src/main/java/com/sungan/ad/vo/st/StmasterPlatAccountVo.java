package com.sungan.ad.vo.st;

import com.sungan.ad.dao.model.StmasterPlatAccount;

import java.math.BigDecimal;

/**
 * 说明:
 */
public class StmasterPlatAccountVo  extends StmasterPlatAccount {
    public StmasterPlatAccountVo() {
        this.setAccountAmount(BigDecimal.ZERO);
        this.setClearAmount(BigDecimal.ZERO);
        this.setCurrentDayAmount(BigDecimal.ZERO);
    }
}