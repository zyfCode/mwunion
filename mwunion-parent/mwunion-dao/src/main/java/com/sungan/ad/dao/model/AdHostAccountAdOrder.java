package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
public class AdHostAccountAdOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private String adOrderId;
	private String accountId;   //账号ID
	private String adHostId;    //广告主ID
	@StatusCn(dictId=EnumAdHostAccountAdOrderType.DICT_KEY)
	private String adOrderType;
	private Long hitCount;
	private Long showCount;
	@StatusCn(dictId=EnumAdHostAccountAdOrderStatus.DICT_KEY)
	private String adOrderStaus;
	private BigDecimal adHostAmount; // 广告主消费
	private BigDecimal stmasterAmount; // 站长消费
	private BigDecimal platAmount; // 平台盈利
	private Integer version;        //版本号
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
	
	
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
	public BigDecimal getStmasterAmount() {
		return stmasterAmount;
	}
	public void setStmasterAmount(BigDecimal stmasterAmount) {
		this.stmasterAmount = stmasterAmount;
	}
	public BigDecimal getPlatAmount() {
		return platAmount;
	}
	public void setPlatAmount(BigDecimal platAmount) {
		this.platAmount = platAmount;
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
