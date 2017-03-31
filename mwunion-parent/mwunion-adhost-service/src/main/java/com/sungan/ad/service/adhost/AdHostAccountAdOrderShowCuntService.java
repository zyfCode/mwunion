package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrderShowCunt;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderShowCuntVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderShowCuntService extends  MuService{
	AdHostAccountAdOrderShowCuntVo find(Long id);
	String insert(AdHostAccountAdOrderShowCunt record);
	void delete(Long id);
	void update(AdHostAccountAdOrderShowCunt task);
	List<AdHostAccountAdOrderShowCuntVo> queryList(AdHostAccountAdOrderShowCunt condition);
	AdPager<AdHostAccountAdOrderShowCuntVo> queryPager(AdHostAccountAdOrderShowCunt condition,int pageIndex,int rows);
}
