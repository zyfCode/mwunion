package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountSerialDAO;
import com.sungan.ad.dao.model.AdHostAccountSerial;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountSerialService;
import com.sungan.ad.service.adhost.vo.AdHostAccountSerialVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountSerialServiceImpl implements AdHostAccountSerialService{
	
	@Autowired
	private AdHostAccountSerialDAO adHostAccountSerialDAO;
	

	public AdHostAccountSerialDAO getAdHostAccountSerialDAO() {
		return adHostAccountSerialDAO;
	}

	public void setAdHostAccountSerialDAO(AdHostAccountSerialDAO adHostAccountSerialDAO) {
		this.adHostAccountSerialDAO = adHostAccountSerialDAO;
	}

	@Override
	public String insert(AdHostAccountSerial record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setSerialId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountSerialDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountSerialVo> queryList(AdHostAccountSerial condition) {
		List<AdHostAccountSerial> query = (List<AdHostAccountSerial>) adHostAccountSerialDAO.query(condition);
		 List<AdHostAccountSerialVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountSerialVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		AdHostAccountSerial find = this.adHostAccountSerialDAO.find(id);
		if (find != null) {
			adHostAccountSerialDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountSerialVo find(Long id) {
		AdHostAccountSerial find = adHostAccountSerialDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountSerialVo parseToVo = AnnotationParser.parseToVo(AdHostAccountSerialVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountSerialVo> queryPager(AdHostAccountSerial condition, int pageIndex, int rows) {
		AdPager<AdHostAccountSerial> queryPage = adHostAccountSerialDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccountSerial> result = queryPage.getRows();
		List<AdHostAccountSerialVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountSerialVo.class, result);
		AdPager<AdHostAccountSerialVo> resultVo = new AdPager<AdHostAccountSerialVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccountSerial record) {
		if(record.getSerialId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccountSerial find = adHostAccountSerialDAO.find(record.getSerialId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountSerialDAO.update(find);
		
	}
}





