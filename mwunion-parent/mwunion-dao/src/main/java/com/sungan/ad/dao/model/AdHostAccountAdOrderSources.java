package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *       资源路径
 * @author zhangyf
 * @date 2017年3月31日
 */
public class AdHostAccountAdOrderSources implements Serializable {
	private static final long serialVersionUID = 1L;
	private String adOrderId;
	private String accountId; // 账号ID
	private String adHostId; // 广告主ID

	@Id
	private String sourceId; // 资源ID
	private String sourceType; // 资源类型

	private String sourceName;// 资源名称

	private String sourcePath;// 本地路径（内网）

	private String sourceUrl; // 公网访问路径
	
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;


	public String getAccountId() {
		return accountId;
	}

	public String getAdOrderId() {
		return adOrderId;
	}

	public void setAdOrderId(String adOrderId) {
		this.adOrderId = adOrderId;
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

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourcePath() {
		return sourcePath;
	}

	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
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
