package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.sungan.ad.dao.model.adenum.EnumStmasterStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *    站点表
 * @author zhangyf
 * @date 2017年3月28日
 */
public class StmasterSite implements Serializable {
	private static final long serialVersionUID = 1L; 
	//站点ID
	private String siteId;
	//站长ID
	private String stId;
	//站点名称 
	private String siteName;
	//站点URL
	private String siteUrl;
	//计费权重
	private String countWeight;
	//站点状态
	@StatusCn(dictId=EnumStmasterStatus.DICT_KEY)
	private String siteStatus;
	
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
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getCountWeight() {
		return countWeight;
	}
	public void setCountWeight(String countWeight) {
		this.countWeight = countWeight;
	}
	public String getSiteStatus() {
		return siteStatus;
	}
	public void setSiteStatus(String siteStatus) {
		this.siteStatus = siteStatus;
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
