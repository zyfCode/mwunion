package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountAdOrderShowCuntDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrderShowCunt;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderShowCuntService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderShowCuntVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderShowCuntServiceImpl implements AdHostAccountAdOrderShowCuntService{
	
	@Autowired
	private AdHostAccountAdOrderShowCuntDAO adHostAccountAdOrderShowCuntDAO;
	

	public AdHostAccountAdOrderShowCuntDAO getAdHostAccountAdOrderShowCuntDAO() {
		return adHostAccountAdOrderShowCuntDAO;
	}

	public void setAdHostAccountAdOrderShowCuntDAO(AdHostAccountAdOrderShowCuntDAO adHostAccountAdOrderShowCuntDAO) {
		this.adHostAccountAdOrderShowCuntDAO = adHostAccountAdOrderShowCuntDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrderShowCunt record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setCountId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountAdOrderShowCuntDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountAdOrderShowCuntVo> queryList(AdHostAccountAdOrderShowCunt condition) {
		List<AdHostAccountAdOrderShowCunt> query = (List<AdHostAccountAdOrderShowCunt>) adHostAccountAdOrderShowCuntDAO.query(condition);
		 List<AdHostAccountAdOrderShowCuntVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderShowCuntVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountAdOrderShowCunt find = this.adHostAccountAdOrderShowCuntDAO.find(id);
		if (find != null) {
			adHostAccountAdOrderShowCuntDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderShowCuntVo find(String id) {
		AdHostAccountAdOrderShowCunt find = adHostAccountAdOrderShowCuntDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderShowCuntVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderShowCuntVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderShowCuntVo> queryPager(AdHostAccountAdOrderShowCunt condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrderShowCunt> queryPage = adHostAccountAdOrderShowCuntDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrderShowCunt> result = queryPage.getRows();
		List<AdHostAccountAdOrderShowCuntVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderShowCuntVo.class, result);
		AdPager<AdHostAccountAdOrderShowCuntVo> resultVo = new AdPager<AdHostAccountAdOrderShowCuntVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountAdOrderShowCunt record) {
		if(record.getCountId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrderShowCunt find = adHostAccountAdOrderShowCuntDAO.find(record.getCountId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderShowCuntDAO.update(find);
		
	}
}





