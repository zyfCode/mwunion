package com.sungan.ad.service.adhost;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccountAdOrder;
import com.sungan.ad.dao.model.AdHostAccountAdOrderAttri;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderVo;

/**
 * 说明:
 */
public interface AdHostAccountAdOrderService extends  MuService{
	AdHostAccountAdOrderVo find(String id);
	String insert(AdHostAccountAdOrder record,AdHostAccountAdOrderAttri attri,List<String> anaxFiles);
	void delete(String id);
	void update(AdHostAccountAdOrder task,AdHostAccountAdOrderAttri attri);
	List<AdHostAccountAdOrderVo> queryList(AdHostAccountAdOrder condition);
	AdPager<AdHostAccountAdOrderVo> queryPager(AdHostAccountAdOrder condition,int pageIndex,int rows);

	/**
	 * 确认订单
	 * @param orderId
	 */
	void comfireOrder(String orderId);

	/**
	 * 暂停订单
	 * @param orderId
	 */
	void stopOrder(String orderId);
	/**
	 * 终止订单
	 * @param orderId
	 */
	void invalidOrder(String orderId);

	/**
	 * 启用订单
	 * @param orderId
	 */
	void enable(String orderId);
}
