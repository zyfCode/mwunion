package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountAdOrderSourcesDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrderSources;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderSourcesService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderSourcesVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderSourcesServiceImpl implements AdHostAccountAdOrderSourcesService{
	
	@Autowired
	private AdHostAccountAdOrderSourcesDAO adHostAccountAdOrderSourcesDAO;
	

	public AdHostAccountAdOrderSourcesDAO getAdHostAccountAdOrderSourcesDAO() {
		return adHostAccountAdOrderSourcesDAO;
	}

	public void setAdHostAccountAdOrderSourcesDAO(AdHostAccountAdOrderSourcesDAO adHostAccountAdOrderSourcesDAO) {
		this.adHostAccountAdOrderSourcesDAO = adHostAccountAdOrderSourcesDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrderSources record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setSourceId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountAdOrderSourcesDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountAdOrderSourcesVo> queryList(AdHostAccountAdOrderSources condition) {
		List<AdHostAccountAdOrderSources> query = (List<AdHostAccountAdOrderSources>) adHostAccountAdOrderSourcesDAO.query(condition);
		 List<AdHostAccountAdOrderSourcesVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderSourcesVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AdHostAccountAdOrderSources find = this.adHostAccountAdOrderSourcesDAO.find(id);
		if (find != null) {
			adHostAccountAdOrderSourcesDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderSourcesVo find(Long id) {
		AdHostAccountAdOrderSources find = adHostAccountAdOrderSourcesDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderSourcesVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderSourcesVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderSourcesVo> queryPager(AdHostAccountAdOrderSources condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrderSources> queryPage = adHostAccountAdOrderSourcesDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrderSources> result = queryPage.getRows();
		List<AdHostAccountAdOrderSourcesVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderSourcesVo.class, result);
		AdPager<AdHostAccountAdOrderSourcesVo> resultVo = new AdPager<AdHostAccountAdOrderSourcesVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountAdOrderSources record) {
		if(record.getSourceId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrderSources find = adHostAccountAdOrderSourcesDAO.find(record.getSourceId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderSourcesDAO.update(find);
		
	}
}





