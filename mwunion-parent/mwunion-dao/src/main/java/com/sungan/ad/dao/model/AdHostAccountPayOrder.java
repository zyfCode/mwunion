package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sungan.ad.dao.model.adenum.EnumAdHostAccountPayOrderStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *     广告主账号
 * @author zhangyf
 * @date 2017年3月29日
 */
public class AdHostAccountPayOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	private String payOrderId;
	private String accountId;   //账号ID
	private String adHostId;    //广告主ID
	private BigDecimal accountAmount; // 充值金额
	@StatusCn(dictId=EnumAdHostAccountPayOrderStatus.DICT_KEY)
	private String payOrderStatus;
	private Integer version;        //版本号
	private Date sureTime;        //确认时间
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
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
}
