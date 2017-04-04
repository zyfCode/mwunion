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
 *       资源路径
 * @author zhangyf
 * @date 2017年3月31日
 */
@Entity
@Table(name="t_adhost_account_adordersources")
public class AdHostAccountAdOrderSources implements AdObject {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String sourceId; // 资源ID
	@Column(length=64,nullable=false)
	private String adOrderId;
	@Column(length=64,nullable=false)
	private String accountId; // 账号ID
	@Column(length=64,nullable=false)
	private String adHostId; // 广告主ID
	@Column(length=64)
	private String sourceType; // 资源类型
	@Column(length=64)
	private String sourceName;// 资源名称
	@Column(length=256)
	private String sourcePath;// 本地路径（内网）
	@Column(length=256)
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
