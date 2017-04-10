package com.sungan.ad.dao.model;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.expand.common.annotation.DateToStr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明:
 *     站长账号-平台账户表
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_stmaster_plat_account")
public class StmasterPlatAccount implements AdObject {
	private static final long serialVersionUID = 1L;
	@Id
	private String accountId;   //账号ID
	@Column(length=64,nullable=false)
	private String stmasterId;    //站长ID
	@Column(length=64,nullable=false)
	private String userId;    //站长ID
	private BigDecimal accountAmount; // 账户余额
	private BigDecimal clearAmount; // 结算金额
	private BigDecimal currentDayAmount; // 冻结金额
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


	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getStmasterId() {
		return stmasterId;
	}

	public void setStmasterId(String stmasterId) {
		this.stmasterId = stmasterId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getClearAmount() {
		return clearAmount;
	}

	public void setClearAmount(BigDecimal clearAmount) {
		this.clearAmount = clearAmount;
	}

	public BigDecimal getCurrentDayAmount() {
		return currentDayAmount;
	}

	public void setCurrentDayAmount(BigDecimal currentDayAmount) {
		this.currentDayAmount = currentDayAmount;
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
