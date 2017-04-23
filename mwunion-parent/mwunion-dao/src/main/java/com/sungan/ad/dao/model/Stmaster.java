package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumUserStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *    部长表
 * @author zhangyf
 * @date 2017年3月27日
 */
@Entity
@Table(name="t_stmaster")
public class Stmaster implements AdObject {
	private static final long serialVersionUID = 1L;
	/**
	 * 站长id
	 */
	@Id
	private String stmasterId;

	@Column(length = 64)
	private String stmasterName;
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


	public static final String USERSTATUS_COLUMN = "userStatus";
	@StatusCn(dictId  = EnumUserStatus.DICT_KEY)
	private String userStatus;
	/**
	 * 接待员id
	 */
	private String recId;
	/**
	 * 接待员名称
	 */
	@Column(length=64)
	private String recName;
	/**
	 * 站长qq
	 */
	@Column(length=64)
	private String stmasterQQ;
	/**
	 * 站长email
	 */
	@Column(length=64)
	private String stmasterEmail;
	/**
	 * 站长手机
	 */
	@Column(length=64)
	private String mobile;
	/**
	 * 站长微信
	 */
	@Column(length=64)
	private String weChat;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;
	@Column(length=128)
	private String remark;

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getStmasterName() {
		return stmasterName;
	}

	public void setStmasterName(String stmasterName) {
		this.stmasterName = stmasterName;
	}

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
