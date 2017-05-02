package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import com.sungan.ad.dao.AdHostDAO;
import com.sungan.ad.dao.PlatProductDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrderAttri;
import com.sungan.ad.dao.model.PlatProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountAdOrderDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrder;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderServiceImpl implements AdHostAccountAdOrderService{
	
	@Autowired
	private AdHostAccountAdOrderDAO adHostAccountAdOrderDAO;
	@Autowired
	private AdHostDAO adHostDAO;

	@Autowired
	private PlatProductDAO platProductDAO;


	public AdHostAccountAdOrderDAO getAdHostAccountAdOrderDAO() {
		return adHostAccountAdOrderDAO;
	}

	public void setAdHostAccountAdOrderDAO(AdHostAccountAdOrderDAO adHostAccountAdOrderDAO) {
		this.adHostAccountAdOrderDAO = adHostAccountAdOrderDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrder record,AdHostAccountAdOrderAttri attri) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAdOrderId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		PlatProduct platProduct = platProductDAO.find(record.getProductId());
		record.setProductName(platProduct.getProductName());
		attri.setAccountId(record.getAccountId());
		attri.setAdHostId(record.getAdHostId());
		attri.setAdOrderAtrriId(IdGeneratorFactory.nextId());
		attri.setCreateTime(new Date());
		attri.setUpdateTime(new Date());
		adHostAccountAdOrderDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountAdOrderVo> queryList(AdHostAccountAdOrder condition) {
		List<AdHostAccountAdOrder> query = (List<AdHostAccountAdOrder>) adHostAccountAdOrderDAO.query(condition);
		 List<AdHostAccountAdOrderVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountAdOrder find = this.adHostAccountAdOrderDAO.find(id);
		if (find != null) {
			adHostAccountAdOrderDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderVo find(String id) {
		AdHostAccountAdOrder find = adHostAccountAdOrderDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderVo> queryPager(AdHostAccountAdOrder condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrder> queryPage = adHostAccountAdOrderDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrder> result = queryPage.getRows();
		List<AdHostAccountAdOrderVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderVo.class, result);
		AdPager<AdHostAccountAdOrderVo> resultVo = new AdPager<AdHostAccountAdOrderVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountAdOrder record) {
		if(record.getAdOrderId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrder find = adHostAccountAdOrderDAO.find(record.getAdOrderId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderDAO.update(find);
		
	}
}





