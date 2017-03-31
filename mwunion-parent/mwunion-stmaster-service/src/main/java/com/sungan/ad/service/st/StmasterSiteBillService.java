package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterSiteBill;
import com.sungan.ad.vo.st.StmasterSiteBillVo;

/**
 * 说明:
 */
public interface StmasterSiteBillService extends  MuService{
	StmasterSiteBillVo find(Long id);
	String insert(StmasterSiteBill record);
	void delete(Long id);
	void update(StmasterSiteBill task);
	List<StmasterSiteBillVo> queryList(StmasterSiteBill condition);
	AdPager<StmasterSiteBillVo> queryPager(StmasterSiteBill condition,int pageIndex,int rows);
}
