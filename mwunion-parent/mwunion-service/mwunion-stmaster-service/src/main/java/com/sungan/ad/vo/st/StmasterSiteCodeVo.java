package com.sungan.ad.vo.st;

import com.sungan.ad.dao.model.StmasterSiteCode;

/**
 * 说明:
 */
public class StmasterSiteCodeVo  extends StmasterSiteCode{
    private String codeStatusCn;

    private String siteName;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCodeStatusCn() {
        return codeStatusCn;
    }

    public void setCodeStatusCn(String codeStatusCn) {
        this.codeStatusCn = codeStatusCn;
    }
}