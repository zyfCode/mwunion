package com.sungan.ad.vo.st;

import com.sungan.ad.dao.model.StmasterSite;

/**
 * 说明:
 */
public class StmasterSiteVo  extends StmasterSite{
    private String siteStatusCn;

    public String getSiteStatusCn() {
        return siteStatusCn;
    }

    public void setSiteStatusCn(String siteStatusCn) {
        this.siteStatusCn = siteStatusCn;
    }
}