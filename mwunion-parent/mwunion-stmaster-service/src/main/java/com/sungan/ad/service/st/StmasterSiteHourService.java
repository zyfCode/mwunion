package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterSiteHour;
import com.sungan.ad.vo.st.StmasterSiteHourVo;

/**
 * 说明:
 */
public interface StmasterSiteHourService extends  MuService{
	StmasterSiteHourVo find(String id);
	String insert(StmasterSiteHour record);
	void delete(String id);
	void update(StmasterSiteHour task);
	List<StmasterSiteHourVo> queryList(StmasterSiteHour condition);
	AdPager<StmasterSiteHourVo> queryPager(StmasterSiteHour condition,int pageIndex,int rows);
}
