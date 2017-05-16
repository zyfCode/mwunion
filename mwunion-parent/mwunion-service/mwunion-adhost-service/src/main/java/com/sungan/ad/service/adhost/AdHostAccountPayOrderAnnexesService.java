package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountPayOrderAnnexes;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderAnnexesVo;

/**
 * 说明:
 */
public interface AdHostAccountPayOrderAnnexesService extends  MuService{
	AdHostAccountPayOrderAnnexesVo find(String id);
	String insert(AdHostAccountPayOrderAnnexes record);
	void delete(String id);
	void update(AdHostAccountPayOrderAnnexes task);
	List<AdHostAccountPayOrderAnnexesVo> queryList(AdHostAccountPayOrderAnnexes condition);
	AdPager<AdHostAccountPayOrderAnnexesVo> queryPager(AdHostAccountPayOrderAnnexes condition,int pageIndex,int rows);
}
