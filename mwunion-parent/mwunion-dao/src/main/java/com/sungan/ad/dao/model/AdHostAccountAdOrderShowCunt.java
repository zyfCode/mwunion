package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月31日
 */
public class AdHostAccountAdOrderShowCunt implements Serializable {
	private static final long serialVersionUID = 1L;
	private String adOrderId;
	private String accountId;   //账号ID
	private String adHostId;    //广告主ID
	private String countId;
	private Long hitCount;
	private Long showCount;
	private Long uvCournt;
	private Long ipCount;
	private Integer flowDate;
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
	public String getCountId() {
		return countId;
	}
	public void setCountId(String countId) {
		this.countId = countId;
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
	public Long getUvCournt() {
		return uvCournt;
	}
	public void setUvCournt(Long uvCournt) {
		this.uvCournt = uvCournt;
	}
	public Long getIpCount() {
		return ipCount;
	}
	public void setIpCount(Long ipCount) {
		this.ipCount = ipCount;
	}
	public Integer getFlowDate() {
		return flowDate;
	}
	public void setFlowDate(Integer flowDate) {
		this.flowDate = flowDate;
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
