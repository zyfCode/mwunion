package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountAdOrderHourDAO;
import com.sungan.ad.dao.model.AdHostAccountAdOrderHour;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderHourService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderHourVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountAdOrderHourServiceImpl implements AdHostAccountAdOrderHourService{
	
	@Autowired
	private AdHostAccountAdOrderHourDAO adHostAccountAdOrderHourDAO;
	

	public AdHostAccountAdOrderHourDAO getAdHostAccountAdOrderHourDAO() {
		return adHostAccountAdOrderHourDAO;
	}

	public void setAdHostAccountAdOrderHourDAO(AdHostAccountAdOrderHourDAO adHostAccountAdOrderHourDAO) {
		this.adHostAccountAdOrderHourDAO = adHostAccountAdOrderHourDAO;
	}

	@Override
	public String insert(AdHostAccountAdOrderHour record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setSerialId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountAdOrderHourDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountAdOrderHourVo> queryList(AdHostAccountAdOrderHour condition) {
		List<AdHostAccountAdOrderHour> query = (List<AdHostAccountAdOrderHour>) adHostAccountAdOrderHourDAO.query(condition);
		 List<AdHostAccountAdOrderHourVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderHourVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccountAdOrderHour find = this.adHostAccountAdOrderHourDAO.find(id);
		if (find != null) {
			adHostAccountAdOrderHourDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountAdOrderHourVo find(String id) {
		AdHostAccountAdOrderHour find = adHostAccountAdOrderHourDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountAdOrderHourVo parseToVo = AnnotationParser.parseToVo(AdHostAccountAdOrderHourVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountAdOrderHourVo> queryPager(AdHostAccountAdOrderHour condition, int pageIndex, int rows) {
		AdPager<AdHostAccountAdOrderHour> queryPage = adHostAccountAdOrderHourDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountAdOrderHour> result = queryPage.getRows();
		List<AdHostAccountAdOrderHourVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountAdOrderHourVo.class, result);
		AdPager<AdHostAccountAdOrderHourVo> resultVo = new AdPager<AdHostAccountAdOrderHourVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountAdOrderHour record) {
		if(record.getSerialId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountAdOrderHour find = adHostAccountAdOrderHourDAO.find(record.getSerialId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountAdOrderHourDAO.update(find);
		
	}
}





