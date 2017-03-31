package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.service.adhost.vo.AdHostAccountVo;

/**
 * 说明:
 */
public interface AdHostAccountService extends  MuService{
	AdHostAccountVo find(Long id);
	String insert(AdHostAccount record);
	void delete(Long id);
	void update(AdHostAccount task);
	List<AdHostAccountVo> queryList(AdHostAccount condition);
	AdPager<AdHostAccountVo> queryPager(AdHostAccount condition,int pageIndex,int rows);
}
