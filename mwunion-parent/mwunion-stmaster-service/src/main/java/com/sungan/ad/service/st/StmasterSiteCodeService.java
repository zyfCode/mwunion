package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterSiteCode;
import com.sungan.ad.vo.st.StmasterSiteCodeVo;

/**
 * 说明:
 */
public interface StmasterSiteCodeService extends  MuService{
	StmasterSiteCodeVo find(Long id);
	String insert(StmasterSiteCode record);
	void delete(Long id);
	void update(StmasterSiteCode task);
	List<StmasterSiteCodeVo> queryList(StmasterSiteCode condition);
	AdPager<StmasterSiteCodeVo> queryPager(StmasterSiteCode condition,int pageIndex,int rows);
}
