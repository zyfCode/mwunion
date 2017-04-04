package com.sungan.ad.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumStmasterSiteHourStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *    站点小时流水表
 * @author zhangyf
 * @date 2017年3月28日
 */
@Entity
@Table(name="t_stmaster_sitehour")
public class StmasterSiteHour implements AdObject {
	private static final long serialVersionUID = 1L; 
	//流水ID
	@Id
	private String stSerialId;
	//站点ID
	@Column(length=64,nullable=false)
	private String siteId;
	//站长ID
	@Column(length=64,nullable=false)
	private String stId;
	//站点名称 
	@Column(length=64)
	private String siteName;
	//展示数
	private Integer showCount;
	//点击数
	private Integer hitCount;
	//IP数
	private Integer ipCount;
	//流量开始时间
	@DateToStr
	private Date beginTime;
	//流量结束时间
	@DateToStr
	private Date endTime;
	//结算流水号
	@Column(length=64)
	private String settlementNo;
	//结算金额
	private BigDecimal settlementAmount;
	//结算日期
	@Column(length=64)
	private Date settlementTime;
	//流量日期，精确到天
	private Integer recordDay;
	//状态 0是未清算 1已清算
	@StatusCn(dictId=EnumStmasterSiteHourStatus.DICT_KEY)
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
	public String getStSerialId() {
		return stSerialId;
	}
	public void setStSerialId(String stSerialId) {
		this.stSerialId = stSerialId;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public Integer getShowCount() {
		return showCount;
	}
	public void setShowCount(Integer showCount) {
		this.showCount = showCount;
	}
	public Integer getHitCount() {
		return hitCount;
	}
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}
	public Integer getIpCount() {
		return ipCount;
	}
	public void setIpCount(Integer ipCount) {
		this.ipCount = ipCount;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getSettlementNo() {
		return settlementNo;
	}
	public void setSettlementNo(String settlementNo) {
		this.settlementNo = settlementNo;
	}
	public BigDecimal getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(BigDecimal settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
	public Date getSettlementTime() {
		return settlementTime;
	}
	public void setSettlementTime(Date settlementTime) {
		this.settlementTime = settlementTime;
	}
 
	public Integer getRecordDay() {
		return recordDay;
	}
	public void setRecordDay(Integer recordDay) {
		this.recordDay = recordDay;
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
