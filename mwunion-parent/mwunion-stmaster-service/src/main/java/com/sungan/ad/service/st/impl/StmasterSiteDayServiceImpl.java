package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterSiteDayDAO;
import com.sungan.ad.dao.model.StmasterSiteDay;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterSiteDayService;
import com.sungan.ad.vo.st.StmasterSiteDayVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterSiteDayServiceImpl implements StmasterSiteDayService{
	
	@Autowired
	private StmasterSiteDayDAO stmasterSiteDayDAO;
	

	public StmasterSiteDayDAO getStmasterSiteDayDAO() {
		return stmasterSiteDayDAO;
	}

	public void setStmasterSiteDayDAO(StmasterSiteDayDAO stmasterSiteDayDAO) {
		this.stmasterSiteDayDAO = stmasterSiteDayDAO;
	}

	@Override
	public String insert(StmasterSiteDay record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setStSerialId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterSiteDayDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterSiteDayVo> queryList(StmasterSiteDay condition) {
		List<StmasterSiteDay> query = (List<StmasterSiteDay>) stmasterSiteDayDAO.query(condition);
		 List<StmasterSiteDayVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteDayVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		StmasterSiteDay find = this.stmasterSiteDayDAO.find(id);
		if (find != null) {
			stmasterSiteDayDAO.delete(find);
		}
	}
	
	@Override
	public StmasterSiteDayVo find(String id) {
		StmasterSiteDay find = stmasterSiteDayDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterSiteDayVo parseToVo = AnnotationParser.parseToVo(StmasterSiteDayVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterSiteDayVo> queryPager(StmasterSiteDay condition, int pageIndex, int rows) {
		AdPager<StmasterSiteDay> queryPage = stmasterSiteDayDAO.queryPage(condition, pageIndex, rows);
		List<StmasterSiteDay> result = queryPage.getRows();
		List<StmasterSiteDayVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteDayVo.class, result);
		AdPager<StmasterSiteDayVo> resultVo = new AdPager<StmasterSiteDayVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterSiteDay record) {
		if(record.getStSerialId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterSiteDay find = stmasterSiteDayDAO.find(record.getStSerialId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterSiteDayDAO.update(find);
		
	}
}





