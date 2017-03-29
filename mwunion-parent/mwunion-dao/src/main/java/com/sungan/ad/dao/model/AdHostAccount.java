package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *     广告主账号
 * @author zhangyf
 * @date 2017年3月29日
 */
public class AdHostAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	private String accountId;   //账号ID
	private String adHostId;    //广告主ID
	private BigDecimal accountAmount; // 账户余额
	private BigDecimal useAmount; // 已经用金额
	private BigDecimal frozenAmount; // 冻结金额
	private Integer version;        //版本号
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

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

	public BigDecimal getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}

	public BigDecimal getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(BigDecimal frozenAmount) {
		this.frozenAmount = frozenAmount;
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
