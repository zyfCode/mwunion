package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrderAttri;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderAttriVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderAttriService extends  MuService{
	AdHostAccountAdOrderAttriVo find(String id);
	String insert(AdHostAccountAdOrderAttri record);
	void delete(String id);
	void update(AdHostAccountAdOrderAttri task);
	List<AdHostAccountAdOrderAttriVo> queryList(AdHostAccountAdOrderAttri condition);
	AdPager<AdHostAccountAdOrderAttriVo> queryPager(AdHostAccountAdOrderAttri condition,int pageIndex,int rows);
}
