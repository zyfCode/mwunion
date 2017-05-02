package com.sungan.ad.service.plat;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.PlatProduct;
import com.sungan.ad.vo.PlatProductVo;

/**
 * 说明:
 */
public interface PlatProductService extends  MuService{
	PlatProductVo find(String id);
	String insert(PlatProduct record);
	void delete(String id);
	void update(PlatProduct task);
	List<PlatProductVo> queryList(PlatProduct condition);
	AdPager<PlatProductVo> queryPager(PlatProduct condition, int pageIndex, int rows);

	/**
	 * 发布产品
	 * @param productId
	 */
    void pubicproduct(String productId);
	/**
	 * 产品下架
	 * @param productId
	 */
    void downShelfProduct(String productId);
}
