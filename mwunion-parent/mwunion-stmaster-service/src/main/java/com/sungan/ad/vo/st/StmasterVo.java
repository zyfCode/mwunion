package com.sungan.ad.vo.st;

import com.sungan.ad.dao.model.Stmaster;

/**
 * 说明:
 */
public class StmasterVo  extends Stmaster{
    private String userStatusCn;
    private String createTimeStr;
    private String updateTimeStr;

    public String getUserStatusCn() {
        return userStatusCn;
    }

    public void setUserStatusCn(String userStatusCn) {
        this.userStatusCn = userStatusCn;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getUpdateTimeStr() {
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }
}