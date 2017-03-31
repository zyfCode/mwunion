package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrder;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderService extends  MuService{
	AdHostAccountAdOrderVo find(Long id);
	String insert(AdHostAccountAdOrder record);
	void delete(Long id);
	void update(AdHostAccountAdOrder task);
	List<AdHostAccountAdOrderVo> queryList(AdHostAccountAdOrder condition);
	AdPager<AdHostAccountAdOrderVo> queryPager(AdHostAccountAdOrder condition,int pageIndex,int rows);
}
