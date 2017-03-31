package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterSiteDay;
import com.sungan.ad.vo.st.StmasterSiteDayVo;

/**
 * 说明:
 */
public interface StmasterSiteDayService extends  MuService{
	StmasterSiteDayVo find(Long id);
	String insert(StmasterSiteDay record);
	void delete(Long id);
	void update(StmasterSiteDay task);
	List<StmasterSiteDayVo> queryList(StmasterSiteDay condition);
	AdPager<StmasterSiteDayVo> queryPager(StmasterSiteDay condition,int pageIndex,int rows);
}
