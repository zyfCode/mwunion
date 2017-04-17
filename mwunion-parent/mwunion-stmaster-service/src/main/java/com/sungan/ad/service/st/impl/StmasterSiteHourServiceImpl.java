package com.sungan.ad.service.st.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.sungan.ad.commons.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterSiteHourDAO;
import com.sungan.ad.dao.model.StmasterSiteHour;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterSiteHourService;
import com.sungan.ad.vo.st.StmasterSiteHourVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterSiteHourServiceImpl implements StmasterSiteHourService{
	
	@Autowired
	private StmasterSiteHourDAO stmasterSiteHourDAO;
	

	public StmasterSiteHourDAO getStmasterSiteHourDAO() {
		return stmasterSiteHourDAO;
	}

	public void setStmasterSiteHourDAO(StmasterSiteHourDAO stmasterSiteHourDAO) {
		this.stmasterSiteHourDAO = stmasterSiteHourDAO;
	}

	@Override
	public String insert(StmasterSiteHour record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setStSerialId(nextId);
		Date date = new Date();
		record.setCreateTime(date);
		record.setUpdateTime(date);
		stmasterSiteHourDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterSiteHourVo> queryList(StmasterSiteHour condition) {
		List<StmasterSiteHour> query = (List<StmasterSiteHour>) stmasterSiteHourDAO.query(condition);
		 List<StmasterSiteHourVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteHourVo.class, query);
		return parseToVoList;
	}

	@Override
	public List<StmasterSiteHourVo> queryListToday(String stSerialId) {
		StmasterSiteHour contidtion = new StmasterSiteHour();
		contidtion.setStSerialId(stSerialId);
		Integer recordHour = DateUtil.getHour();
		contidtion.setRecordHour(recordHour);
		List<StmasterSiteHour> query = (List<StmasterSiteHour>) this.stmasterSiteHourDAO.query(contidtion);
		List<StmasterSiteHourVo> stmasterSiteHourVos = AnnotationParser.parseToVoList(StmasterSiteHourVo.class, query);
		return stmasterSiteHourVos;
	}

	@Override
	public void delete(String id) {
		StmasterSiteHour find = this.stmasterSiteHourDAO.find(id);
		if (find != null) {
			stmasterSiteHourDAO.delete(find);
		}
	}
	
	@Override
	public StmasterSiteHourVo find(String id) {
		StmasterSiteHour find = stmasterSiteHourDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterSiteHourVo parseToVo = AnnotationParser.parseToVo(StmasterSiteHourVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterSiteHourVo> queryPager(StmasterSiteHour condition, int pageIndex, int rows) {
		AdPager<StmasterSiteHour> queryPage = stmasterSiteHourDAO.queryPage(condition, pageIndex, rows);
		List<StmasterSiteHour> result = queryPage.getRows();
		List<StmasterSiteHourVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteHourVo.class, result);
		AdPager<StmasterSiteHourVo> resultVo = new AdPager<StmasterSiteHourVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterSiteHour record) {
		if(record.getStSerialId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterSiteHour find = stmasterSiteHourDAO.find(record.getStSerialId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterSiteHourDAO.update(find);
		
	}
}





