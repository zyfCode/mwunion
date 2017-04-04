package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumStmasterStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *    站点表
 * @author zhangyf
 * @date 2017年3月28日
 */
@Entity
@Table(name="t_stmaster_site")
public class StmasterSite implements AdObject {
	private static final long serialVersionUID = 1L; 
	//站点ID
	@Id
	private String siteId;
	//站长ID
	@Column(length=64,nullable=false)
	private String stId;
	//站点名称 
	@Column(length=64)
	private String siteName;
	//站点URL
	@Column(length=256)
	private String siteUrl;
	//计费权重
	private Integer countWeight;
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

	public Integer getCountWeight() {
		return countWeight;
	}
	public void setCountWeight(Integer countWeight) {
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
