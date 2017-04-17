package com.sungan.ad.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumStmasterSiteBillStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明: 站点结算账单 
 * @author zhangyf
 * @date 2017年3月28日
 */
@Entity
@Table(name="t_stmaster_sitebill")
public class StmasterSiteBill implements AdObject {
	private static final long serialVersionUID = 1L;
	// 流水ID
	@Id
	private String stBillId;
	// 站点ID
	@Column(length=64,nullable=false)
	private String siteId;
	// 站长ID
	@Column(length=64,nullable=false)
	private String stId;
	// 结算流水号
	@Column(length=64)
	private String stBillNo;
	//月
	private Integer mothOfYear;
	//周次
	private Integer weekOfMonth;
	// 应结算金额
	private BigDecimal settlementAmount;
	// 未结算余额
	private BigDecimal settlementAmountBalance;
	// 已经结算金额
	private BigDecimal clearAmount;
	// 结算日期
	@DateToStr
	private Date settlementTime;
	// 状态 0是未清算 1已清算
	@StatusCn(dictId = EnumStmasterSiteBillStatus.DICT_KEY)
	private String billStatus;
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

	public String getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
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
