package com.sungan.ad.controller.validBean;

import com.sungan.ad.dao.model.AdHostAccountAdOrder;

import java.math.BigDecimal;

/**
 * 说明:
 */
public class AdHostAccountAdOrderValid  extends AdHostAccountAdOrder{
    private BigDecimal adAmount;

    public BigDecimal getAdAmount() {
        return adAmount;
    }

    public void setAdAmount(BigDecimal adAmount) {
        this.adAmount = adAmount;
    }
}