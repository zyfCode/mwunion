package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;

import com.sungan.ad.dao.model.adenum.EnumStmasterSiteBillStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明: 站点结算账单 
 * @author zhangyf
 * @date 2017年3月28日
 */
public class StmasterSiteBill implements Serializable {
	private static final long serialVersionUID = 1L;
	// 站点ID
	private String siteId;
	// 站长ID
	private String stId;
	// 流水ID
	@Id
	private String stBillId;
	// 结算流水号
	private String stBillNo;
	private Integer mothOfYear;
	private Integer weekOfMonth;
	// 应结算金额
	private BigDecimal settlementAmount;
	// 未结算余额
	private BigDecimal settlementAmountBalance;
	// 已经结算金额
	private BigDecimal clearAmount;
	// 结算日期
	private Date settlementTime;
	// 状态 0是未清算 1已清算
	@StatusCn(dictId = EnumStmasterSiteBillStatus.DICT_KEY)
	private String hourStatus;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getStId() {
		return stId;
	}

	public void setStId(String stId) {
		this.stId = stId;
	}

	public String getStBillId() {
		return stBillId;
	}

	public void setStBillId(String stBillId) {
		this.stBillId = stBillId;
	}

	public String getStBillNo() {
		return stBillNo;
	}

	public void setStBillNo(String stBillNo) {
		this.stBillNo = stBillNo;
	}

	public Integer getMothOfYear() {
		return mothOfYear;
	}

	public void setMothOfYear(Integer mothOfYear) {
		this.mothOfYear = mothOfYear;
	}

	public Integer getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(Integer weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}

	public BigDecimal getSettlementAmountBalance() {
		return settlementAmountBalance;
	}

	public void setSettlementAmountBalance(BigDecimal settlementAmountBalance) {
		this.settlementAmountBalance = settlementAmountBalance;
	}

	public BigDecimal getClearAmount() {
		return clearAmount;
	}

	public void setClearAmount(BigDecimal clearAmount) {
		this.clearAmount = clearAmount;
	}

	public Date getSettlementTime() {
		return settlementTime;
	}

	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}

	public String getHourStatus() {
		return hourStatus;
	}

	public void setHourStatus(String hourStatus) {
		this.hourStatus = hourStatus;
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
