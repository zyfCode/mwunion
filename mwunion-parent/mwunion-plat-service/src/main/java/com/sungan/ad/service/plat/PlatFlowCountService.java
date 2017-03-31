package com.sungan.ad.service.plat;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.PlatFlowCount;
import com.sungan.ad.vo.PlatFlowCountVo;

/**
 * 说明:
 */
public interface PlatFlowCountService extends  MuService{
	PlatFlowCountVo find(Long id);
	String insert(PlatFlowCount record);
	void delete(Long id);
	void update(PlatFlowCount task);
	List<PlatFlowCountVo> queryList(PlatFlowCount condition);
	AdPager<PlatFlowCountVo> queryPager(PlatFlowCount condition,int pageIndex,int rows);
}
