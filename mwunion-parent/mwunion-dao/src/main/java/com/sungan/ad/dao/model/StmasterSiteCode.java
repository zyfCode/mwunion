package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *    站点代码表
 * @author zhangyf
 * @date 2017年3月28日
 */
@Entity
@Table(name="t_stmaster_sitecode")
public class StmasterSiteCode implements AdObject {
	private static final long serialVersionUID = 1L; 
	//站点代码
	@Id
	private String siteCodeId;
	//代码ID
	@Column(length=64,nullable=false)
	private String siteId;
	//站长ID
	@Column(length=64,nullable=false)
	private String stId;
	@Column(length=512)
	private String siteCodeUrl;
	@Column(columnDefinition="TEXT")
	private String siteCodeContent;
	@Column(length=64)
	private String siteCodeName;
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
	public String getSiteCodeId() {
		return siteCodeId;
	}
	public void setSiteCodeId(String siteCodeId) {
		this.siteCodeId = siteCodeId;
	}
	public String getStId() {
		return stId;
	}
	public void setStId(String stId) {
		this.stId = stId;
	}
	public String getSiteCodeUrl() {
		return siteCodeUrl;
	}
	public void setSiteCodeUrl(String siteCodeUrl) {
		this.siteCodeUrl = siteCodeUrl;
	}
	public String getSiteCodeContent() {
		return siteCodeContent;
	}
	public void setSiteCodeContent(String siteCodeContent) {
		this.siteCodeContent = siteCodeContent;
	}
	public String getSiteCodeName() {
		return siteCodeName;
	}
	public void setSiteCodeName(String siteCodeName) {
		this.siteCodeName = siteCodeName;
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
