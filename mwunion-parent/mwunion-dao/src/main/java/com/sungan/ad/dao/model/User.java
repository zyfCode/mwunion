package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumUserStatus;
import com.sungan.ad.dao.model.adenum.EnumUserType;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 *     客户表
 * @author zhangyf
 * @date 2017年3月26日
 */
@Entity
@Table(name="t_user")
public class User implements AdObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户id*/
	@Id
	private String userId;
	/**用户名称 */
	@Column(length=64)
	private String userName;
	/**用户账号*/
	@Column(length=64)
	private String userAcount;
	/**用户密码*/
	@Column(length=256)
	private String userPwd;
	/**创建时间*/
	@DateToStr
	private Date createTime;
	/**修改时间*/
	@DateToStr
	private Date updateTime;
	/**
	 * 0正常   1停用   2注销
	 */
	@StatusCn(dictId=EnumUserStatus.DICT_KEY)
	@Column(length=2)
	private String userStatus;
	/**
	 * 0 站长 1 广告主
	 */
	@StatusCn(dictId=EnumUserType.DICT_KEY)
	@Column(length=2)
	private String userType;
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
	public String getUserAcount() {
		return userAcount;
	}
	public void setUserAcount(String userAcount) {
		this.userAcount = userAcount;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
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
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
}
