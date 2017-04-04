package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostDAO;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostService;
import com.sungan.ad.service.adhost.vo.AdHostVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostServiceImpl implements AdHostService{
	
	@Autowired
	private AdHostDAO adHostDAO;
	

	public AdHostDAO getAdHostDAO() {
		return adHostDAO;
	}

	public void setAdHostDAO(AdHostDAO adHostDAO) {
		this.adHostDAO = adHostDAO;
	}

	@Override
	public String insert(AdHost record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAdhostId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostVo> queryList(AdHost condition) {
		List<AdHost> query = (List<AdHost>) adHostDAO.query(condition);
		 List<AdHostVo> parseToVoList = AnnotationParser.parseToVoList(AdHostVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHost find = this.adHostDAO.find(id);
		if (find != null) {
			adHostDAO.delete(find);
		}
	}
	
	@Override
	public AdHostVo find(String id) {
		AdHost find = adHostDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostVo parseToVo = AnnotationParser.parseToVo(AdHostVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostVo> queryPager(AdHost condition, int pageIndex, int rows) {
		AdPager<AdHost> queryPage = adHostDAO.queryPage(condition, pageIndex, rows);
		List<AdHost> result = queryPage.getRows();
		List<AdHostVo> parseToVoList = AnnotationParser.parseToVoList(AdHostVo.class, result);
		AdPager<AdHostVo> resultVo = new AdPager<AdHostVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHost record) {
		if(record.getAdhostId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHost find = adHostDAO.find(record.getAdhostId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostDAO.update(find);
		
	}
}





