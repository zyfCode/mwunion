package com.sungan.ad.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderPayType;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderStatus;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountAdOrderType;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *     广告主订单表
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_adhost_account_adorder")
public class AdHostAccountAdOrder implements AdObject {
	private static final long serialVersionUID = 1L;
	@Id
	private String adOrderId;
	@Column(length=64,nullable=false)
	private String accountId;   //账号ID
	@Column(length=64,nullable=false)
	private String adHostId;    //广告主ID
	private String adHostName;
	private String productId;
	private String productName;
	private Long hitCount;
	@StatusCn(dictId=EnumAdHostAccountAdOrderType.DICT_KEY)
	@Column(length=2)
	private String adOrderType;
	@StatusCn(dictId= EnumAdHostAccountAdOrderPayType.DICT_KEY)
	@Column(length=2)
	private String payType;
	private Long showCount;
	@StatusCn(dictId=EnumAdHostAccountAdOrderStatus.DICT_KEY)
	private String adOrderStaus;
	@Column(precision = 19, scale = 6)
	private BigDecimal adHostAmount; // 广告主消费
	private Integer version;        //版本号
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getAdHostName() {
		return adHostName;
	}

	public void setAdHostName(String adHostName) {
		this.adHostName = adHostName;
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

	public String getAdOrderId() {
		return adOrderId;
	}
	public void setAdOrderId(String adOrderId) {
		this.adOrderId = adOrderId;
	}
	public String getAdOrderStaus() {
		return adOrderStaus;
	}
	public void setAdOrderStaus(String adOrderStaus) {
		this.adOrderStaus = adOrderStaus;
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
	public String getAdOrderType() {
		return adOrderType;
	}
	public void setAdOrderType(String adOrderType) {
		this.adOrderType = adOrderType;
	}
	public Long getHitCount() {
		return hitCount;
	}
	public void setHitCount(Long hitCount) {
		this.hitCount = hitCount;
	}
	public Long getShowCount() {
		return showCount;
	}
	public void setShowCount(Long showCount) {
		this.showCount = showCount;
	}
	public BigDecimal getAdHostAmount() {
		return adHostAmount;
	}
	public void setAdHostAmount(BigDecimal adHostAmount) {
		this.adHostAmount = adHostAmount;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
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
