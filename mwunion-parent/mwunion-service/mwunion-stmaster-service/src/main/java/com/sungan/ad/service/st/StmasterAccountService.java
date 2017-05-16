package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.StmasterAccount;
import com.sungan.ad.vo.st.StmasterAccountVo;

/**
 * 说明:
 */
public interface StmasterAccountService extends  MuService{
	StmasterAccountVo find(String id);
	String insert(StmasterAccount record);
	void delete(String id);
	void update(StmasterAccount task);
	List<StmasterAccountVo> queryList(StmasterAccount condition);
	AdPager<StmasterAccountVo> queryPager(StmasterAccount condition,int pageIndex,int rows);
}
