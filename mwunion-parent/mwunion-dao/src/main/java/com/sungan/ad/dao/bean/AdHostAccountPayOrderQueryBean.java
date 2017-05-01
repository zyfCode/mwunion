package com.sungan.ad.dao.bean;

import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.dao.model.AdHostAccountPayOrder;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountPayOrderStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.SqlColumn;
import com.sungan.ad.expand.common.annotation.StatusCn;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/4/30.
 */
public class AdHostAccountPayOrderQueryBean implements Serializable{

    @SqlColumn(AdHostAccountPayOrder.class)
    private String payOrderId;
    @SqlColumn(AdHostAccountPayOrder.class)
    private String accountId;   //账号ID
    @SqlColumn(AdHostAccountPayOrder.class)
    private String adHostId;    //广告主ID
    @SqlColumn(AdHostAccountPayOrder.class)
    private BigDecimal accountAmount; // 充值金额
    @StatusCn(dictId= EnumAdHostAccountPayOrderStatus.DICT_KEY)
    @SqlColumn(AdHostAccountPayOrder.class)
    private String payOrderStatus;
    @SqlColumn(AdHostAccountPayOrder.class)
    private Integer version;        //版本号
    @DateToStr
    @SqlColumn(AdHostAccountPayOrder.class)
    private Date sureTime;        //确认时间
    @DateToStr
    @SqlColumn(AdHostAccountPayOrder.class)
    private Date createTime;
    @DateToStr
    @SqlColumn(AdHostAccountPayOrder.class)
    private Date updateTime;
    @SqlColumn(AdHost.class)
    private String adhostName;

    public String getPayOrderId() {
        return payOrderId;
    }

    public void setPayOrderId(String payOrderId) {
        this.payOrderId = payOrderId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAdHostId() {
        return adHostId;
    }

    public void setAdHostId(String adHostId) {
        this.adHostId = adHostId;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public String getPayOrderStatus() {
        return payOrderStatus;
    }

    public void setPayOrderStatus(String payOrderStatus) {
        this.payOrderStatus = payOrderStatus;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getSureTime() {
        return sureTime;
    }

    public void setSureTime(Date sureTime) {
        this.sureTime = sureTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAdhostName() {
        return adhostName;
    }

    public void setAdhostName(String adhostName) {
        this.adhostName = adhostName;
    }
}
