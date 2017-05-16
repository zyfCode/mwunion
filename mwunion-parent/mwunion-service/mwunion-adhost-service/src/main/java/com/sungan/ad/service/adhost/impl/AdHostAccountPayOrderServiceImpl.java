package com.sungan.ad.service.adhost.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sungan.ad.dao.AdHostAccountPayOrderQueryDAO;
import com.sungan.ad.dao.bean.AdHostAccountPayOrderQueryBean;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderQueryBeanVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountPayOrderDAO;
import com.sungan.ad.dao.model.AdHostAccountPayOrder;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountPayOrderService;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountPayOrderServiceImpl implements AdHostAccountPayOrderService{
	
	@Autowired
	private AdHostAccountPayOrderDAO adHostAccountPayOrderDAO;

	@Autowired
	private AdHostAccountPayOrderQueryDAO queryDAO;



	public AdHostAccountPayOrderDAO getAdHostAccountPayOrderDAO() {
		return adHostAccountPayOrderDAO;
	}


	public AdPager<AdHostAccountPayOrderQueryBeanVo> queryPager(AdHostAccountPayOrderQueryBean condition, int pageIndex, int rows){
		AdPager<AdHostAccountPayOrderQueryBean> result = queryDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountPayOrderQueryBeanVo> parseToVoList = new ArrayList<AdHostAccountPayOrderQueryBeanVo>();
		if(result.getRows()!=null) {
			parseToVoList = AnnotationParser.parseToVoList(AdHostAccountPayOrderQueryBeanVo.class, result.getRows());
		}
		AdPager<AdHostAccountPayOrderQueryBeanVo> resultVo = new AdPager<AdHostAccountPayOrderQueryBeanVo>(pageIndex, rows, result.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}

	public void setAdHostAccountPayOrderDAO(AdHostAccountPayOrderDAO adHostAccountPayOrderDAO) {
		this.adHostAccountPayOrderDAO = adHostAccountPayOrderDAO;
	}

	@Override
	public String insert(AdHostAccountPayOrder record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setPayOrderId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountPayOrderDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountPayOrderVo> queryList(AdHostAccountPayOrder condition) {
		List<AdHostAccountPayOrder> query = (List<AdHostAccountPayOrder>) adHostAccountPayOrderDAO.query(condition);
		 List<AdHostAccountPayOrderVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountPayOrderVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountPayOrder find = this.adHostAccountPayOrderDAO.find(id);
		if (find != null) {
			adHostAccountPayOrderDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountPayOrderVo find(String id) {
		AdHostAccountPayOrder find = adHostAccountPayOrderDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountPayOrderVo parseToVo = AnnotationParser.parseToVo(AdHostAccountPayOrderVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountPayOrderVo> queryPager(AdHostAccountPayOrder condition, int pageIndex, int rows) {
		AdPager<AdHostAccountPayOrder> queryPage = adHostAccountPayOrderDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountPayOrder> result = queryPage.getRows();
		List<AdHostAccountPayOrderVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountPayOrderVo.class, result);
		AdPager<AdHostAccountPayOrderVo> resultVo = new AdPager<AdHostAccountPayOrderVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountPayOrder record) {
		if(record.getPayOrderId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountPayOrder find = adHostAccountPayOrderDAO.find(record.getPayOrderId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountPayOrderDAO.update(find);
		
	}
}





