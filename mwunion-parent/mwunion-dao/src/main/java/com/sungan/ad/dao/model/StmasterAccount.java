package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明: 账户表
 * 		
 * @author zhangyf
 * @date 2017年3月28日
 */
public class StmasterAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	// 站长ID
	private String stId;
	// 账户ID
	private String accountId;
	// 账户类型 0 支付宝 1 微信 2 银联
	private String accountType;
	// 开户地址
	private String accountAddr;
	// 收款人
	private String accountMan;
	// 收款人账号
	private String accountNo;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountAddr() {
		return accountAddr;
	}

	public void setAccountAddr(String accountAddr) {
		this.accountAddr = accountAddr;
	}

	public String getAccountMan() {
		return accountMan;
	}

	public void setAccountMan(String accountMan) {
		this.accountMan = accountMan;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
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
