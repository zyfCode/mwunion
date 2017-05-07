package com.sungan.ad.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountPayOrderStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *     广告主账号
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_adhost_payorder")
public class AdHostAccountPayOrder implements AdObject {
	private static final long serialVersionUID = 1L;
	public String getPayOrderId() {
		return payOrderId;
	}
	@Id
	private String payOrderId;
	@Column(length=64,nullable=false)
	private String accountId;   //账号ID
	@Column(length=64,nullable=false)
	private String adHostId;    //广告主ID
	@Column(precision = 19, scale = 6)
	private BigDecimal accountAmount; // 充值金额
	@StatusCn(dictId=EnumAdHostAccountPayOrderStatus.DICT_KEY)
	private String payOrderStatus;
	private Integer version;        //版本号
	@DateToStr
	private Date sureTime;        //确认时间
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
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
