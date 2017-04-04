package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.vo.st.StmasterSiteVo;

/**
 * 说明:
 */
public interface StmasterSiteService extends  MuService{
	StmasterSiteVo find(String id);
	String insert(StmasterSite record);
	void delete(String id);
	void update(StmasterSite task);
	List<StmasterSiteVo> queryList(StmasterSite condition);
	AdPager<StmasterSiteVo> queryPager(StmasterSite condition,int pageIndex,int rows);
}
