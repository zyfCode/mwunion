package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrderDay;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderDayVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderDayService extends  MuService{
	AdHostAccountAdOrderDayVo find(String id);
	String insert(AdHostAccountAdOrderDay record);
	void delete(String id);
	void update(AdHostAccountAdOrderDay task);
	List<AdHostAccountAdOrderDayVo> queryList(AdHostAccountAdOrderDay condition);
	AdPager<AdHostAccountAdOrderDayVo> queryPager(AdHostAccountAdOrderDay condition,int pageIndex,int rows);
}
