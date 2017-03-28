package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *     账单附件
 * @author zhangyf
 * @date 2017年3月29日
 */
public class StmasterSiteBillAnnexes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String siteBillId;
	private String annxId;   //附件ID
	private String annexesName; //附件名称 
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
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
