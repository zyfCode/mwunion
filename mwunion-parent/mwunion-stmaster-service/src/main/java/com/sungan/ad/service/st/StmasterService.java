package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.vo.st.StmasterVo;

/**
 * 说明:
 */
public interface StmasterService extends  MuService{
	StmasterVo find(String id);
	String insert(Stmaster record);
	void delete(String id);
	void update(Stmaster task);
	List<StmasterVo> queryList(Stmaster condition);
	AdPager<StmasterVo> queryPager(Stmaster condition,int pageIndex,int rows);
}
