package com.sungan.ad.dao.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 说明:
 *     记录流量   此表需要分表
 * @author zhangyf
 * @date 2017年3月31日
 */
public class PlatFlowCount implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String flowId;            //流量 id
	private String orderId;           //订单id
	private String adHostId;          //广告主
	private String stMasterId;        //站长id
	private String stMasterSiteId;   //站点ID
	private String uvId;             //UV记录
	private String sourcesIp;       //访问ip
	private String sourcePath;      //来路path
	private String userAgent;       //访问特征
	private String screenSize;      //屏幕分辨率
	private Date vistTime;          //访问 时间
	private Date createTime;
	private Date updateTime;
	
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAdHostId() {
		return adHostId;
	}
	public void setAdHostId(String adHostId) {
		this.adHostId = adHostId;
	}
	public String getStMasterId() {
		return stMasterId;
	}
	public void setStMasterId(String stMasterId) {
		this.stMasterId = stMasterId;
	}
	public String getStMasterSiteId() {
		return stMasterSiteId;
	}
	public void setStMasterSiteId(String stMasterSiteId) {
		this.stMasterSiteId = stMasterSiteId;
	}
	public String getUvId() {
		return uvId;
	}
	public void setUvId(String uvId) {
		this.uvId = uvId;
	}
	public String getSourcesIp() {
		return sourcesIp;
	}
	public void setSourcesIp(String sourcesIp) {
		this.sourcesIp = sourcesIp;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public String getUserAgent() {
		return userAgent;
	}
	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}
	public String getScreenSize() {
		return screenSize;
	}
	public void setScreenSize(String screenSize) {
		this.screenSize = screenSize;
	}
	public Date getVistTime() {
		return vistTime;
	}
	public void setVistTime(Date vistTime) {
		this.vistTime = vistTime;
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
