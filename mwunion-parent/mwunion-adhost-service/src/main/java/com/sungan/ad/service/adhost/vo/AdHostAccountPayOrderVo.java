package com.sungan.ad.service.adhost.vo;

import com.sungan.ad.dao.model.AdHostAccountPayOrder;
import com.sungan.ad.expand.common.annotation.DateToStr;

import java.util.Date;

/**
 * 说明:
 */
public class AdHostAccountPayOrderVo  extends AdHostAccountPayOrder{
    private String payOrderStatusCn;
    private String sureTimeStr;        //确认时间
    private String createTimeStr;
    private String updateTimeStr;

    public String getPayOrderStatusCn() {
        return payOrderStatusCn;
    }

    public void setPayOrderStatusCn(String payOrderStatusCn) {
        this.payOrderStatusCn = payOrderStatusCn;
    }

    public String getSureTimeStr() {
        return sureTimeStr;
    }

    public void setSureTimeStr(String sureTimeStr) {
        this.sureTimeStr = sureTimeStr;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}