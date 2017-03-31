package com.sungan.ad.service.plat.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.PlatMesgDAO;
import com.sungan.ad.dao.model.PlatMesg;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.plat.PlatMesgService;
import com.sungan.ad.vo.PlatMesgVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class PlatMesgServiceImpl implements PlatMesgService{
	
	@Autowired
	private PlatMesgDAO platMesgDAO;
	

	public PlatMesgDAO getPlatMesgDAO() {
		return platMesgDAO;
	}

	public void setPlatMesgDAO(PlatMesgDAO platMesgDAO) {
		this.platMesgDAO = platMesgDAO;
	}

	@Override
	public String insert(PlatMesg record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setMsgId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		platMesgDAO.insert(record);
		return nextId;
	}

	@Override
	public List<PlatMesgVo> queryList(PlatMesg condition) {
		List<PlatMesg> query = (List<PlatMesg>) platMesgDAO.query(condition);
		 List<PlatMesgVo> parseToVoList = AnnotationParser.parseToVoList(PlatMesgVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		PlatMesg find = this.platMesgDAO.find(id);
		if (find != null) {
			platMesgDAO.delete(find);
		}
	}
	
	@Override
	public PlatMesgVo find(Long id) {
		PlatMesg find = platMesgDAO.find(id);
		if(find==null){
			return null;
		}
		PlatMesgVo parseToVo = AnnotationParser.parseToVo(PlatMesgVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<PlatMesgVo> queryPager(PlatMesg condition, int pageIndex, int rows) {
		AdPager<PlatMesg> queryPage = platMesgDAO.queryPage(condition, pageIndex, rows);
		List<PlatMesg> result = queryPage.getRows();
		List<PlatMesgVo> parseToVoList = AnnotationParser.parseToVoList(PlatMesgVo.class, result);
		AdPager<PlatMesgVo> resultVo = new AdPager<PlatMesgVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(PlatMesg record) {
		if(record.getMsgId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		PlatMesg find = platMesgDAO.find(record.getMsgId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		platMesgDAO.update(find);
		
	}
}





