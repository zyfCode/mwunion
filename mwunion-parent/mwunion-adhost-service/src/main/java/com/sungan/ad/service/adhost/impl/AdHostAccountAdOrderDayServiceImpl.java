package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountAdOrderDayDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrderDay;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderDayService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderDayVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderDayServiceImpl implements AdHostAccountAdOrderDayService{
	
	@Autowired
	private AdHostAccountAdOrderDayDAO adHostAccountAdOrderDayDAO;
	

	public AdHostAccountAdOrderDayDAO getAdHostAccountAdOrderDayDAO() {
		return adHostAccountAdOrderDayDAO;
	}

	public void setAdHostAccountAdOrderDayDAO(AdHostAccountAdOrderDayDAO adHostAccountAdOrderDayDAO) {
		this.adHostAccountAdOrderDayDAO = adHostAccountAdOrderDayDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrderDay record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setSerialId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountAdOrderDayDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountAdOrderDayVo> queryList(AdHostAccountAdOrderDay condition) {
		List<AdHostAccountAdOrderDay> query = (List<AdHostAccountAdOrderDay>) adHostAccountAdOrderDayDAO.query(condition);
		 List<AdHostAccountAdOrderDayVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderDayVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AdHostAccountAdOrderDay find = this.adHostAccountAdOrderDayDAO.find(id);
		if (find != null) {
			adHostAccountAdOrderDayDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderDayVo find(Long id) {
		AdHostAccountAdOrderDay find = adHostAccountAdOrderDayDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderDayVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderDayVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderDayVo> queryPager(AdHostAccountAdOrderDay condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrderDay> queryPage = adHostAccountAdOrderDayDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrderDay> result = queryPage.getRows();
		List<AdHostAccountAdOrderDayVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderDayVo.class, result);
		AdPager<AdHostAccountAdOrderDayVo> resultVo = new AdPager<AdHostAccountAdOrderDayVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountAdOrderDay record) {
		if(record.getSerialId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrderDay find = adHostAccountAdOrderDayDAO.find(record.getSerialId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderDayDAO.update(find);
		
	}
}





