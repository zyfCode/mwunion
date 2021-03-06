package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumUserMsgStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_user_mesg")
public class UserMesg implements AdObject {
	private static final long serialVersionUID = 1L;
	@Id
	private String msgId;  //消息ID
	@Column(length=64)
	private String userId;   //用户ID
	@Column(length=64)
	private String msgContent;  //消息内容
	@DateToStr
	private Date showTime;      //显示时间
	@StatusCn(dictId=EnumUserMsgStatus.DICT_KEY)
	@Column(length=2)
	private String msgStatus;   //消息状态 0 未提醒 1 已提醒 2 提醒确认 3 失效
	@Column(length=64)
	private String createMan;   //创建人
	@DateToStr
	private Date invalidTime;   //失效时间
	@DateToStr
	private Date createTime;    
	@DateToStr
	private Date updateTime;
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Date getShowTime() {
		return showTime;
	}
	public void setShowTime(Date showTime) {
		this.showTime = showTime;
	}
	public String getMsgStatus() {
		return msgStatus;
	}
	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}
	public String getCreateMan() {
		return createMan;
	}
	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}
	public Date getInvalidTime() {
		return invalidTime;
	}
	public void setInvalidTime(Date invalidTime) {
		this.invalidTime = invalidTime;
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
