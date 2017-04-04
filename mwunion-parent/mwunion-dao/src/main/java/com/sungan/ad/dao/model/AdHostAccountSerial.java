package com.sungan.ad.dao.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumAdHostAccountSerialType;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明: 广告主账户流水表
 * 
 * @author zhangyf
 * @date 2017年3月29日
 */
@Entity
@Table(name="t_adhost_accountserial")
public class AdHostAccountSerial implements AdObject {
	private static final long serialVersionUID = 1L;
	@Id
	private String serialId; // 流水ID
	@Column(length=64,nullable=false)
	private String accountId; // 账号ID
	@Column(length=64,nullable=false)
	private String adHostId; // 广告主ID
	private BigDecimal accountAmount; // 流水金额
	
    @StatusCn(dictId=EnumAdHostAccountSerialType.DICT_KEY)
    @Column(length=2)
	private String serilType;
	@DateToStr
	private Date createTime;
	@DateToStr
	private Date updateTime;

	public String getSerialId() {
		return serialId;
	}

	public void setSerialId(String serialId) {
		this.serialId = serialId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAdHostId() {
		return adHostId;
	}

	public void setAdHostId(String adHostId) {
		this.adHostId = adHostId;
	}

	public BigDecimal getAccountAmount() {
		return accountAmount;
	}

	public void setAccountAmount(BigDecimal accountAmount) {
		this.accountAmount = accountAmount;
	}

	public String getSerilType() {
		return serilType;
	}

	public void setSerilType(String serilType) {
		this.serilType = serilType;
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
