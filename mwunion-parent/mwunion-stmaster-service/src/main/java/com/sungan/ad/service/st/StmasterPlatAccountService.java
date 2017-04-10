package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterPlatAccount;
import com.sungan.ad.vo.st.StmasterPlatAccountVo;

/**
 * 说明:
 */
public interface StmasterPlatAccountService extends  MuService{
	StmasterPlatAccountVo find(Long id);
	String insert(StmasterPlatAccount record);
	void delete(Long id);
	void update(StmasterPlatAccount task);
	List<StmasterPlatAccountVo> queryList(StmasterPlatAccount condition);
	AdPager<StmasterPlatAccountVo> queryPager(StmasterPlatAccount condition, int pageIndex, int rows);
}
