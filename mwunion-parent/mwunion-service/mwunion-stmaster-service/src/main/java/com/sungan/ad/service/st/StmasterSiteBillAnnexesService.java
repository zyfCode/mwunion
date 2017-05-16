package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterSiteBillAnnexes;
import com.sungan.ad.vo.st.StmasterSiteBillAnnexesVo;

/**
 * 说明:
 */
public interface StmasterSiteBillAnnexesService extends  MuService{
	StmasterSiteBillAnnexesVo find(String id);
	String insert(StmasterSiteBillAnnexes record);
	void delete(String id);
	void update(StmasterSiteBillAnnexes task);
	List<StmasterSiteBillAnnexesVo> queryList(StmasterSiteBillAnnexes condition);
	AdPager<StmasterSiteBillAnnexesVo> queryPager(StmasterSiteBillAnnexes condition,int pageIndex,int rows);
}
