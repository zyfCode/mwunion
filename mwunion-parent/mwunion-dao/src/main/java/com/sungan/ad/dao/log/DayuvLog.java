package com.sungan.ad.dao.log;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    @Id
    private String id;
    @Column(length=64,nullable=false)
    private String adOrderId;
    @Column(length=64,nullable=false)
    private String stId;
    @Column(length=64,nullable=false)
    private String adHostId;
    @Column(length=64)
    private String stSiteId;
    @Column(length=64)
    private String stSiteName;
    @Column(length = 256)
    private String clientAgent;
    @Column(length=64)
    private String uvKey;   //cooki UV记录
    @Column(length=64)
    private String uvIp;
    private Integer accesTimeInt; //访问时间精确到小时yyyyMMddHH
    private Date accesTime;
    @Column(length=2,nullable=false)
    private String adOrderType;  //点击或者展示
    private Date createTime;
    private Date updateTime;

    public String getStId() {
        return stId;
    }

    public void setStId(String stId) {
        this.stId = stId;
    }

    public String getClientAgent() {
        return clientAgent;
    }

    public void setClientAgent(String clientAgent) {
        this.clientAgent = clientAgent;
    }

    public String getStSiteId() {
        return stSiteId;
    }

    public void setStSiteId(String stSiteId) {
        this.stSiteId = stSiteId;
    }

    public String getStSiteName() {
        return stSiteName;
    }

    public void setStSiteName(String stSiteName) {
        this.stSiteName = stSiteName;
    }

    public Integer getAccesTimeInt() {
        return accesTimeInt;
    }

    public void setAccesTimeInt(Integer accesTimeInt) {
        this.accesTimeInt = accesTimeInt;
    }

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
