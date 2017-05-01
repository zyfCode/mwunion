package com.sungan.ad.controller.validBean.view;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by zhangyf18255 on 2017/4/29.
 */
public class AdHostOrder {
    @NotEmpty
    private String adhostId;
    @NotEmpty
    private String moneyAmount;
    private String remark;
    private String anaxNames;

    public String getAdhostId() {
        return adhostId;
    }

    public void setAdhostId(String adhostId) {
        this.adhostId = adhostId;
    }

    public String getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(String moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAnaxNames() {
        return anaxNames;
    }

    public void setAnaxNames(String anaxNames) {
        this.anaxNames = anaxNames;
    }
}
