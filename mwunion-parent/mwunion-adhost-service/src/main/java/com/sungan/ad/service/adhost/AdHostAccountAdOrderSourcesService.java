package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrderSources;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderSourcesVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderSourcesService extends  MuService{
	AdHostAccountAdOrderSourcesVo find(String id);
	String insert(AdHostAccountAdOrderSources record);
	void delete(String id);
	void update(AdHostAccountAdOrderSources task);
	List<AdHostAccountAdOrderSourcesVo> queryList(AdHostAccountAdOrderSources condition);
	AdPager<AdHostAccountAdOrderSourcesVo> queryPager(AdHostAccountAdOrderSources condition,int pageIndex,int rows);
}
