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
    private List<String>andIds;

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

    public List<String> getAndIds() {
        return andIds;
    }

    public void setAndIds(List<String> andIds) {
        this.andIds = andIds;
    }
}
