package com.sungan.ad.dao.model;

import java.util.Date;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.expand.common.annotation.DateToStr;

/**
 * 说明:
 *    部长表
 * @author zhangyf
 * @date 2017年3月27日
 */
public class Stmaster implements AdObject {
	private static final long serialVersionUID = 1L;
	/**
	 * 站长id
	 */
	private String stmasterId;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 接待员id
	 */
	private String recId;
	/**
	 * 接待员名称
	 */
	private String recName;
	/**
	 * 站长qq
	 */
	private String stmasterQQ;
	/**
	 * 站长email
	 */
	private String stmasterEmail;
	/**
	 * 站长手机
	 */
	private String mobile;
	/**
	 * 站长微信
	 */
	private String weChat;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
	
	private String remark;
	

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStmasterId() {
		return stmasterId;
	}

	public void setStmasterId(String stmasterId) {
		this.stmasterId = stmasterId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getStmasterQQ() {
		return stmasterQQ;
	}

	public void setStmasterQQ(String stmasterQQ) {
		this.stmasterQQ = stmasterQQ;
	}

	public String getStmasterEmail() {
		return stmasterEmail;
	}

	public void setStmasterEmail(String stmasterEmail) {
		this.stmasterEmail = stmasterEmail;
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
