package com.sungan.ad.service.plat;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.Reception;
import com.sungan.ad.vo.ReceptionVo;

/**
 * 说明:
 */
public interface ReceptionService extends  MuService{
	ReceptionVo find(String id);
	String insert(Reception record);
	void delete(String id);
	void update(Reception task);
	List<ReceptionVo> queryList(Reception condition);
	AdPager<ReceptionVo> queryPager(Reception condition,int pageIndex,int rows);
}
