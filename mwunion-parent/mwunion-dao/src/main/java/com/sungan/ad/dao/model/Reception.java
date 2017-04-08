package com.sungan.ad.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sungan.ad.common.dao.AdObject;
import com.sungan.ad.dao.model.adenum.EnumRecWorkStatus;
import com.sungan.ad.expand.common.annotation.DateToStr;
import com.sungan.ad.expand.common.annotation.StatusCn;

/**
 * 说明:
 * 接待员
 *
 * @author zhangyf
 * @date 2017年3月27日
 */
@Entity
@Table(name = "t_reception")
public class Reception implements AdObject {

    private static final long serialVersionUID = 1L;
    /**
     * 接待员id
     */
    @Id
    private String recId;
    /**
     * 接待员名称
     */
    @Column(length = 64)
    private String recName;
    /**
     * 用户id
     */
    @Column(length = 64, nullable = false)
    private String userId;
    /**
     * 用户名称
     */
    @Column(length = 64)
    private String userAccount;
    /**
     * 在职状态 0 在职 1离职
     */
    @Column(length = 2)
    @StatusCn(dictId = EnumRecWorkStatus.DICT_KEY)
    private String workStatus;
    @Column(length = 64)
    private String mobile;   //
    @Column(length = 64)
    private String recWeChat;
    @Column(length = 64)
    private String recQQ;
    @DateToStr
    private Date createTime;
    @DateToStr
    private Date updateTime;



    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRecWeChat() {
        return recWeChat;
    }

    public void setRecWeChat(String recWeChat) {
        this.recWeChat = recWeChat;
    }

    public String getRecQQ() {
        return recQQ;
    }

    public void setRecQQ(String recQQ) {
        this.recQQ = recQQ;
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

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }
}
