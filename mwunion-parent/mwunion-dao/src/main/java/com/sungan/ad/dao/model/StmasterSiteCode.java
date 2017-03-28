package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *    站点代码表
 * @author zhangyf
 * @date 2017年3月28日
 */
public class StmasterSiteCode implements Serializable {
	private static final long serialVersionUID = 1L; 
	//代码ID
	private String siteId;
	//站点代码
	private String siteCodeId;
	//站长ID
	private String stId;
	private String siteCodeUrl;
	private String siteCodeContent;
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
