package com.sungan.ad.service.plat;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.PlatMesg;
import com.sungan.ad.vo.PlatMesgVo;

/**
 * 说明:
 */
public interface PlatMesgService extends  MuService{
	PlatMesgVo find(Long id);
	String insert(PlatMesg record);
	void delete(Long id);
	void update(PlatMesg task);
	List<PlatMesgVo> queryList(PlatMesg condition);
	AdPager<PlatMesgVo> queryPager(PlatMesg condition,int pageIndex,int rows);
}
