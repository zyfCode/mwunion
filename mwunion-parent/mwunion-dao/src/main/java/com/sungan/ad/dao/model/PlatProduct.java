package com.sungan.ad.dao.model;

import com.sungan.ad.dao.model.adenum.EnumAdHostAccountPayOrderStatus;
import com.sungan.ad.dao.model.adenum.EnumProductStatus;
import com.sungan.ad.dao.model.adenum.EnumProductType;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zhangyf18255 on 2017/5/1.
 */
@Entity
@Table(name="t_plat_product")
public class PlatProduct implements Serializable {
    @Id
    private String productId;     //
    @Column(length=64,nullable=false)
    private String productName;    //产品名称
    @StatusCn(dictId= EnumProductType.DICT_KEY)
    @Column(length=2,nullable=false)
    private String productType;   //产品类型
    @Column(precision = 19, scale = 6)
    private BigDecimal adhostPrice;  //广告主计价
    @Column(precision = 19, scale = 6)
    private BigDecimal srPrice;     //站长计价
    @StatusCn(dictId = EnumProductStatus.DICT_KEY)
    @Column(length=2,nullable=false)
    private String productStatus;  //产品状态
    @Column(length=64,nullable=false)
    private String publicSource;  // 投放源
    @DateToStr
    private Date createTime;
    @DateToStr
    private Date updateTime;

    public String getPublicSource() {
        return publicSource;
    }

    public void setPublicSource(String publicSource) {
        this.publicSource = publicSource;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getAdhostPrice() {
        return adhostPrice;
    }

    public void setAdhostPrice(BigDecimal adhostPrice) {
        this.adhostPrice = adhostPrice;
    }

    public BigDecimal getSrPrice() {
        return srPrice;
    }

    public void setSrPrice(BigDecimal srPrice) {
        this.srPrice = srPrice;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
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
