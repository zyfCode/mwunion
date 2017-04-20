package com.sungan.ad.controller.validBean.view;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/4/16.
 */
public class StSettleBillVlid {
    @NotEmpty
    private String stBillId;
    @NotEmpty
    private String settlAmount;  // 结算金额
    private String anaxNames;

    public String getStBillId() {
        return stBillId;
    }

    public void setStBillId(String stBillId) {
        this.stBillId = stBillId;
    }

    public String getSettlAmount() {
        return settlAmount;
    }

    public void setSettlAmount(String settlAmount) {
        this.settlAmount = settlAmount;
    }

    public String getAnaxNames() {
        return anaxNames;
    }

    public void setAnaxNames(String anaxNames) {
        this.anaxNames = anaxNames;
    }
}
