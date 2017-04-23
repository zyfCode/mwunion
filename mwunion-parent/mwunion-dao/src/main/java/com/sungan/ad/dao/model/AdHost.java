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
 *    广告主表
 * @author zhangyf
 * @date 2017年3月27日
 */
@Entity
@Table(name="t_adhost")
public class AdHost implements AdObject {
	private static final long serialVersionUID = 1L;
	/**
	 * 广告主id
	 */
	@Id
	private String adhostId;

	/**
	 * 广告主名称
	 */
	private String adhostName;
	/**
	 * 用户名称
	 */
	@Column(length=64)
	private String userAccount;
	/**
	 * 用户id
	 */
	@Column(length=64,nullable=false)
	private String userId;

	/**
	 * 用户名
	 */
	@Column(length=64,nullable=false)
	private String userName;
	/**
	 * 接待员id
	 */
	@Column(length=64)
	private String recId;
	/**
	 * 接待员名称
	 */
	@Column(length=64)
	private String recName;
	/**
	 * 广告主qq
	 */
	@Column(length=64)
	private String adhostQQ;
	/**
	 * 广告主email
	 */
	@Column(length=64)
	private String adhostEmail;
	/**
	 * 广告主手机
	 */
	@Column(length=64)
	private String mobile;
	/**
	 * 广告主微信
	 */
	@Column(length=64)
	private String weChat;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	private String remark;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAdhostName() {
		return adhostName;
	}

	public void setAdhostName(String adhostName) {
		this.adhostName = adhostName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAdhostId() {
		return adhostId;
	}

	public void setAdhostId(String adhostId) {
		this.adhostId = adhostId;
	}

	public String getAdhostQQ() {
		return adhostQQ;
	}

	public void setAdhostQQ(String adhostQQ) {
		this.adhostQQ = adhostQQ;
	}

	public String getAdhostEmail() {
		return adhostEmail;
	}

	public void setAdhostEmail(String adhostEmail) {
		this.adhostEmail = adhostEmail;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWeChat() {
		return weChat;
	}

	public void setWeChat(String weChat) {
		this.weChat = weChat;
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
