package com.sungan.ad.dao.model;

import com.sungan.ad.common.dao.AdObject;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月26日
 */
public class User implements AdObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**用户id*/
	private String userId;
	/**用户名称 */
	private String userName;
	/**用户账号*/
	private String userAcount;
	/**用户密码*/
	private String userPwd;
	/**创建时间*/
	private String createTime;
	/**修改时间*/
	private String updateTime;
	/**
	 * 0正常   1停用   2注销
	 */
	private String userStatus;
	/**
	 * 0 站长 1 广告主
	 */
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
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
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
