package com.sungan.ad.service.plat.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.PlatFlowCountDAO;
import com.sungan.ad.dao.model.PlatFlowCount;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.plat.PlatFlowCountService;
import com.sungan.ad.vo.PlatFlowCountVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class PlatFlowCountServiceImpl implements PlatFlowCountService{
	
	@Autowired
	private PlatFlowCountDAO platFlowCountDAO;
	

	public PlatFlowCountDAO getPlatFlowCountDAO() {
		return platFlowCountDAO;
	}

	public void setPlatFlowCountDAO(PlatFlowCountDAO platFlowCountDAO) {
		this.platFlowCountDAO = platFlowCountDAO;
	}

	@Override
	public String insert(PlatFlowCount record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setFlowId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		platFlowCountDAO.insert(record);
		return nextId;
	}

	@Override
	public List<PlatFlowCountVo> queryList(PlatFlowCount condition) {
		List<PlatFlowCount> query = (List<PlatFlowCount>) platFlowCountDAO.query(condition);
		 List<PlatFlowCountVo> parseToVoList = AnnotationParser.parseToVoList(PlatFlowCountVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		PlatFlowCount find = this.platFlowCountDAO.find(id);
		if (find != null) {
			platFlowCountDAO.delete(find);
		}
	}
	
	@Override
	public PlatFlowCountVo find(String id) {
		PlatFlowCount find = platFlowCountDAO.find(id);
		if(find==null){
			return null;
		}
		PlatFlowCountVo parseToVo = AnnotationParser.parseToVo(PlatFlowCountVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<PlatFlowCountVo> queryPager(PlatFlowCount condition, int pageIndex, int rows) {
		AdPager<PlatFlowCount> queryPage = platFlowCountDAO.queryPage(condition, pageIndex, rows);
		List<PlatFlowCount> result = queryPage.getRows();
		List<PlatFlowCountVo> parseToVoList = AnnotationParser.parseToVoList(PlatFlowCountVo.class, result);
		AdPager<PlatFlowCountVo> resultVo = new AdPager<PlatFlowCountVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(PlatFlowCount record) {
		if(record.getFlowId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		PlatFlowCount find = platFlowCountDAO.find(record.getFlowId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		platFlowCountDAO.update(find);
		
	}
}





