package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountPayOrder;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderVo;

/**
 * 说明:
 */
public interface AdHostAccountPayOrderService extends  MuService{
	AdHostAccountPayOrderVo find(Long id);
	String insert(AdHostAccountPayOrder record);
	void delete(Long id);
	void update(AdHostAccountPayOrder task);
	List<AdHostAccountPayOrderVo> queryList(AdHostAccountPayOrder condition);
	AdPager<AdHostAccountPayOrderVo> queryPager(AdHostAccountPayOrder condition,int pageIndex,int rows);
}
