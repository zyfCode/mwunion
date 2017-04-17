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
 *     账单附件
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_stmaster_sitebillannexes")
public class StmasterSiteBillAnnexes implements AdObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	@Column(length=64,nullable=false)
	private String siteBillId;
	@Column(length=64)
	private String annxId;   //附件ID
	@Column(length=64)
	private String annexesName; //附件名称
	private String annexesUrl; //附件名称
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	public String getAnnexesUrl() {
		return annexesUrl;
	}

	public void setAnnexesUrl(String annexesUrl) {
		this.annexesUrl = annexesUrl;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSiteBillId() {
		return siteBillId;
	}
	public void setSiteBillId(String siteBillId) {
		this.siteBillId = siteBillId;
	}
	public String getAnnxId() {
		return annxId;
	}
	public void setAnnxId(String annxId) {
		this.annxId = annxId;
	}
	public String getAnnexesName() {
		return annexesName;
	}
	public void setAnnexesName(String annexesName) {
		this.annexesName = annexesName;
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
