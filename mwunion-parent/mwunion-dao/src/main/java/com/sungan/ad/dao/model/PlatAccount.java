package com.sungan.ad.dao.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/5/6.
 */
@Entity
@Table(name="t_plat_flowcount")
public class PlatAccount {
    @Id
    private String id;
    private BigDecimal toalAmount;
    private BigDecimal nowAmount;
    private BigDecimal payAmount;
    private Integer version;
    private Date createTime;
    private Date updateTime;


    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getToalAmount() {
        return toalAmount;
    }

    public void setToalAmount(BigDecimal toalAmount) {
        this.toalAmount = toalAmount;
    }

    public BigDecimal getNowAmount() {
        return nowAmount;
    }

    public void setNowAmount(BigDecimal nowAmount) {
        this.nowAmount = nowAmount;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
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
}
