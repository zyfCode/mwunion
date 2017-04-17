package com.sungan.ad.vo.st;

import com.sungan.ad.dao.model.StmasterSiteBill;

/**
 * 说明:
 */
public class StmasterSiteBillVo  extends StmasterSiteBill{
    private String billStatusCn;
    private String createTimeStr;
    private String settlementTimeStr;

    public String getBillStatusCn() {
        return billStatusCn;
    }

    public void setBillStatusCn(String billStatusCn) {
        this.billStatusCn = billStatusCn;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getSettlementTimeStr() {
        return settlementTimeStr;
    }

    public void setSettlementTimeStr(String settlementTimeStr) {
        this.settlementTimeStr = settlementTimeStr;
    }
}