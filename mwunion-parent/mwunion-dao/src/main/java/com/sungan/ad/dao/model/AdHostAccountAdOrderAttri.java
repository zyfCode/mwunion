package com.sungan.ad.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明: 广告主订单表
 * 
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_adhost_account_adorderattri")
public class AdHostAccountAdOrderAttri implements AdObject {
	private static final long serialVersionUID = 1L;
	@Id
	private String adOrderAtrriId;
	@Column(length=64,nullable=false)
	private String adOrderId;
	@Column(length=64,nullable=false)
	private String accountId; // 账号ID
	@Column(length=64,nullable=false)
	private String adHostId; // 广告主ID
	@Column(precision = 19, scale = 6)
	private BigDecimal adAmount; // 消费上限
	@Column(precision = 19, scale = 6)
	private BigDecimal hitPrice; // 点击计费单价
	@Column(precision = 19, scale = 6)
	private BigDecimal showPrice; // 刷量计费单价
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	
	public String getAdOrderAtrriId() {
		return adOrderAtrriId;
	}

	public void setAdOrderAtrriId(String adOrderAtrriId) {
		this.adOrderAtrriId = adOrderAtrriId;
	}

	public String getAdOrderId() {
		return adOrderId;
	}

	public void setAdOrderId(String adOrderId) {
		this.adOrderId = adOrderId;
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

	public BigDecimal getAdAmount() {
		return adAmount;
	}

	public void setAdAmount(BigDecimal adAmount) {
		this.adAmount = adAmount;
	}

	public BigDecimal getHitPrice() {
		return hitPrice;
	}

	public void setHitPrice(BigDecimal hitPrice) {
		this.hitPrice = hitPrice;
	}

	public BigDecimal getShowPrice() {
		return showPrice;
	}

	public void setShowPrice(BigDecimal showPrice) {
		this.showPrice = showPrice;
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
