package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountPayOrderAnnexesDAO;
import com.sungan.ad.dao.model.AdHostAccountPayOrderAnnexes;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountPayOrderAnnexesService;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderAnnexesVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountPayOrderAnnexesServiceImpl implements AdHostAccountPayOrderAnnexesService{
	
	@Autowired
	private AdHostAccountPayOrderAnnexesDAO adHostAccountPayOrderAnnexesDAO;
	

	public AdHostAccountPayOrderAnnexesDAO getAdHostAccountPayOrderAnnexesDAO() {
		return adHostAccountPayOrderAnnexesDAO;
	}

	public void setAdHostAccountPayOrderAnnexesDAO(AdHostAccountPayOrderAnnexesDAO adHostAccountPayOrderAnnexesDAO) {
		this.adHostAccountPayOrderAnnexesDAO = adHostAccountPayOrderAnnexesDAO;
	}

	@Override
	public String insert(AdHostAccountPayOrderAnnexes record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAnnxId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountPayOrderAnnexesDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountPayOrderAnnexesVo> queryList(AdHostAccountPayOrderAnnexes condition) {
		List<AdHostAccountPayOrderAnnexes> query = (List<AdHostAccountPayOrderAnnexes>) adHostAccountPayOrderAnnexesDAO.query(condition);
		 List<AdHostAccountPayOrderAnnexesVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountPayOrderAnnexesVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountPayOrderAnnexes find = this.adHostAccountPayOrderAnnexesDAO.find(id);
		if (find != null) {
			adHostAccountPayOrderAnnexesDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountPayOrderAnnexesVo find(String id) {
		AdHostAccountPayOrderAnnexes find = adHostAccountPayOrderAnnexesDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountPayOrderAnnexesVo parseToVo = AnnotationParser.parseToVo(AdHostAccountPayOrderAnnexesVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountPayOrderAnnexesVo> queryPager(AdHostAccountPayOrderAnnexes condition, int pageIndex, int rows) {
		AdPager<AdHostAccountPayOrderAnnexes> queryPage = adHostAccountPayOrderAnnexesDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountPayOrderAnnexes> result = queryPage.getRows();
		List<AdHostAccountPayOrderAnnexesVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountPayOrderAnnexesVo.class, result);
		AdPager<AdHostAccountPayOrderAnnexesVo> resultVo = new AdPager<AdHostAccountPayOrderAnnexesVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountPayOrderAnnexes record) {
		if(record.getAnnxId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountPayOrderAnnexes find = adHostAccountPayOrderAnnexesDAO.find(record.getAnnxId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountPayOrderAnnexesDAO.update(find);
		
	}
}





