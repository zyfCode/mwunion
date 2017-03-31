package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.service.adhost.vo.AdHostVo;

/**
 * 说明:
 */
public interface AdHostService extends  MuService{
	AdHostVo find(Long id);
	String insert(AdHost record);
	void delete(Long id);
	void update(AdHost task);
	List<AdHostVo> queryList(AdHost condition);
	AdPager<AdHostVo> queryPager(AdHost condition,int pageIndex,int rows);
}
