package com.sungan.ad.service.plat.impl;

import java.util.Date;
import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.model.adenum.EnumProductStatus;
import com.sungan.ad.dao.model.adenum.EnumUserMsgStatus;
import com.sungan.ad.service.plat.PlatProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.dao.PlatProductDAO;
import com.sungan.ad.dao.model.PlatProduct;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.vo.PlatProductVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class PlatProductServiceImpl implements PlatProductService {
	
	@Autowired
	private PlatProductDAO platProductDAO;
	

	public PlatProductDAO getPlatProductDAO() {
		return platProductDAO;
	}

	public void setPlatProductDAO(PlatProductDAO platProductDAO) {
		this.platProductDAO = platProductDAO;
	}

	@Override
	public String insert(PlatProduct record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setProductStatus(EnumProductStatus.UNPUBLIC.getKey());
		record.setProductId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		platProductDAO.insert(record);
		return nextId;
	}

	@Override
	public List<PlatProductVo> queryList(PlatProduct condition) {
		List<PlatProduct> query = (List<PlatProduct>) platProductDAO.query(condition);
		 List<PlatProductVo> parseToVoList = AnnotationParser.parseToVoList(PlatProductVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		PlatProduct find = this.platProductDAO.find(id);
		if (find != null) {
			if(EnumProductStatus.UNPUBLIC!=EnumProductStatus.match(find.getProductStatus())){
				throw new AdRuntimeException("只能删除未发布的产品");
			}
			platProductDAO.delete(find);
		}
	}
	
	@Override
	public PlatProductVo find(String id) {
		PlatProduct find = platProductDAO.find(id);
		if(find==null){
			return null;
		}
		PlatProductVo parseToVo = AnnotationParser.parseToVo(PlatProductVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<PlatProductVo> queryPager(PlatProduct condition, int pageIndex, int rows) {
		AdPager<PlatProduct> queryPage = platProductDAO.queryPage(condition, pageIndex, rows);
		List<PlatProduct> result = queryPage.getRows();
		List<PlatProductVo> parseToVoList = AnnotationParser.parseToVoList(PlatProductVo.class, result);
		AdPager<PlatProductVo> resultVo = new AdPager<PlatProductVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}

	@Override
	public void pubicproduct(String productId) {
		if(productId==null){
			throw new AdRuntimeException("记录ID为空");
		}
		PlatProduct find = platProductDAO.find(productId);
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		if(EnumProductStatus.PUBLIC==EnumProductStatus.match(find.getProductStatus())){
			throw new AdRuntimeException("产品已发布");
		}
		find.setProductStatus(EnumProductStatus.PUBLIC.getKey());
		find.setUpdateTime(new Date());
		platProductDAO.update(find);
	}

	@Override
	public void downShelfProduct(String productId) {
		if(productId==null){
			throw new AdRuntimeException("记录ID为空");
		}
		PlatProduct find = platProductDAO.find(productId);
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		if(EnumProductStatus.DOWNSHELF==EnumProductStatus.match(find.getProductStatus())){
			throw new AdRuntimeException("产品已下架");
		}
		if(EnumProductStatus.UNPUBLIC==EnumProductStatus.match(find.getProductStatus())){
			throw new AdRuntimeException("产品未发布，不允许下架");
		}
		find.setProductStatus(EnumProductStatus.DOWNSHELF.getKey());
		find.setUpdateTime(new Date());
		platProductDAO.update(find);
	}

	@Override
	public void update(PlatProduct record) {
		if(record.getProductId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		PlatProduct find = platProductDAO.find(record.getProductId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		platProductDAO.update(find);
		
	}
}





