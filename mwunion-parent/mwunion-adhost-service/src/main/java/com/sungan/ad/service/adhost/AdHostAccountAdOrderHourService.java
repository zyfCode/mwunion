package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrderHour;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderHourVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderHourService extends  MuService{
	AdHostAccountAdOrderHourVo find(Long id);
	String insert(AdHostAccountAdOrderHour record);
	void delete(Long id);
	void update(AdHostAccountAdOrderHour task);
	List<AdHostAccountAdOrderHourVo> queryList(AdHostAccountAdOrderHour condition);
	AdPager<AdHostAccountAdOrderHourVo> queryPager(AdHostAccountAdOrderHour condition,int pageIndex,int rows);
}
