package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountAdOrderAttriDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrderAttri;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderAttriService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderAttriVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderAttriServiceImpl implements AdHostAccountAdOrderAttriService{
	
	@Autowired
	private AdHostAccountAdOrderAttriDAO adHostAccountAdOrderAttriDAO;
	

	public AdHostAccountAdOrderAttriDAO getAdHostAccountAdOrderAttriDAO() {
		return adHostAccountAdOrderAttriDAO;
	}

	public void setAdHostAccountAdOrderAttriDAO(AdHostAccountAdOrderAttriDAO adHostAccountAdOrderAttriDAO) {
		this.adHostAccountAdOrderAttriDAO = adHostAccountAdOrderAttriDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrderAttri record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAdOrderAtrriId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountAdOrderAttriDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountAdOrderAttriVo> queryList(AdHostAccountAdOrderAttri condition) {
		List<AdHostAccountAdOrderAttri> query = (List<AdHostAccountAdOrderAttri>) adHostAccountAdOrderAttriDAO.query(condition);
		 List<AdHostAccountAdOrderAttriVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderAttriVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountAdOrderAttri find = this.adHostAccountAdOrderAttriDAO.find(id);
		if (find != null) {
			adHostAccountAdOrderAttriDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderAttriVo find(String id) {
		AdHostAccountAdOrderAttri find = adHostAccountAdOrderAttriDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderAttriVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderAttriVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderAttriVo> queryPager(AdHostAccountAdOrderAttri condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrderAttri> queryPage = adHostAccountAdOrderAttriDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrderAttri> result = queryPage.getRows();
		List<AdHostAccountAdOrderAttriVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderAttriVo.class, result);
		AdPager<AdHostAccountAdOrderAttriVo> resultVo = new AdPager<AdHostAccountAdOrderAttriVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountAdOrderAttri record) {
		if(record.getAccountId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrderAttri find = adHostAccountAdOrderAttriDAO.find(record.getAdOrderAtrriId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderAttriDAO.update(find);
		
	}
}





