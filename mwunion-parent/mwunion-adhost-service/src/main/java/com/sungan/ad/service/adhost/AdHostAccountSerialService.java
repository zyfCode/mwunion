package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountSerial;
import com.sungan.ad.service.adhost.vo.AdHostAccountSerialVo;

/**
 * 说明:
 */
public interface AdHostAccountSerialService extends  MuService{
	AdHostAccountSerialVo find(Long id);
	String insert(AdHostAccountSerial record);
	void delete(Long id);
	void update(AdHostAccountSerial task);
	List<AdHostAccountSerialVo> queryList(AdHostAccountSerial condition);
	AdPager<AdHostAccountSerialVo> queryPager(AdHostAccountSerial condition,int pageIndex,int rows);
}
