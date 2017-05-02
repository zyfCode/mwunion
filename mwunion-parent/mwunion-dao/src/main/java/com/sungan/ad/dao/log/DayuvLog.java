package com.sungan.ad.dao.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 流量日志
 * Created by zhangyf18255 on 2017/5/2.
 */
@Entity
@Table(name="t_dayuvlog")
public class DayuvLog implements Serializable{
    @Column(length=64,nullable=false)
    private String id;
    @Column(length=64,nullable=false)
    private String adOrderId;
    @Column(length=64,nullable=false)
    private String adHostId;
    @Column(length=64)
    private String uvKey;   //cooki UV记录
    @Column(length=64)
    private String uvIp;
    private Date accesTime;
    @Column(length=2,nullable=false)
    private String adOrderType;  //点击或者展示
    private Date createTime;
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdOrderId() {
        return adOrderId;
    }

    public void setAdOrderId(String adOrderId) {
        this.adOrderId = adOrderId;
    }

    public String getAdHostId() {
        return adHostId;
    }

    public void setAdHostId(String adHostId) {
        this.adHostId = adHostId;
    }

    public String getUvKey() {
        return uvKey;
    }

    public void setUvKey(String uvKey) {
        this.uvKey = uvKey;
    }

    public String getUvIp() {
        return uvIp;
    }

    public void setUvIp(String uvIp) {
        this.uvIp = uvIp;
    }

    public Date getAccesTime() {
        return accesTime;
    }

    public void setAccesTime(Date accesTime) {
        this.accesTime = accesTime;
    }

    public String getAdOrderType() {
        return adOrderType;
    }

    public void setAdOrderType(String adOrderType) {
        this.adOrderType = adOrderType;
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
