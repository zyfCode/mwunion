package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Id;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *    接待员
 * @author zhangyf
 * @date 2017年3月27日
 */
public class Reception implements AdObject {

	private static final long serialVersionUID = 1L;
	/**接待员id*/
	@Id
	private String  recId;
	/**接待员名称*/
	private String recName;
	/**用户id*/
	private String userId;
	/**用户名称*/
	private String userName;
	/**在职状态 0 在职 1离职*/
	private String workStatus;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
	
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
	public String getRecId() {
		return recId;
	}
	public void setRecId(String recId) {
		this.recId = recId;
	}
	public String getRecName() {
		return recName;
	}
	public void setRecName(String recName) {
		this.recName = recName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWorkStatus() {
		return workStatus;
	}
	public void setWorkStatus(String workStatus) {
		this.workStatus = workStatus;
	}
}
