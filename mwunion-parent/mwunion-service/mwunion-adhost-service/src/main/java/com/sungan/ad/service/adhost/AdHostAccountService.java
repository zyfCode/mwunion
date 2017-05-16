package com.sungan.ad.service.adhost;

import java.math.BigDecimal;
import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.service.adhost.vo.AdHostAccountVo;

/**
 * 说明:
 */
public interface AdHostAccountService extends  MuService{
	AdHostAccountVo find(String id);
	String insert(AdHostAccount record);
	void delete(String id);
	void update(AdHostAccount task);
	List<AdHostAccountVo> queryList(AdHostAccount condition);
	AdPager<AdHostAccountVo> queryPager(AdHostAccount condition,int pageIndex,int rows);

	/**
	 * 添加支付定单
	 * @param moneyAmount
	 * @param remark
	 * @param files
	 */
	void addMoneyOrder(String adhostId, BigDecimal moneyAmount, String remark, List<String> files);
}
