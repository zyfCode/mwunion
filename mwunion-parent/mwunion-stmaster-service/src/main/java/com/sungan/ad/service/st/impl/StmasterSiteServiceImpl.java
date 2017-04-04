package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterSiteDAO;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterSiteService;
import com.sungan.ad.vo.st.StmasterSiteVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterSiteServiceImpl implements StmasterSiteService{
	
	@Autowired
	private StmasterSiteDAO stmasterSiteDAO;
	

	public StmasterSiteDAO getStmasterSiteDAO() {
		return stmasterSiteDAO;
	}

	public void setStmasterSiteDAO(StmasterSiteDAO stmasterSiteDAO) {
		this.stmasterSiteDAO = stmasterSiteDAO;
	}

	@Override
	public String insert(StmasterSite record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setSiteId(nextId);
		//record.setId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterSiteDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterSiteVo> queryList(StmasterSite condition) {
		List<StmasterSite> query = (List<StmasterSite>) stmasterSiteDAO.query(condition);
		 List<StmasterSiteVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		StmasterSite find = this.stmasterSiteDAO.find(id);
		if (find != null) {
			stmasterSiteDAO.delete(find);
		}
	}
	
	@Override
	public StmasterSiteVo find(String id) {
		StmasterSite find = stmasterSiteDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterSiteVo parseToVo = AnnotationParser.parseToVo(StmasterSiteVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterSiteVo> queryPager(StmasterSite condition, int pageIndex, int rows) {
		AdPager<StmasterSite> queryPage = stmasterSiteDAO.queryPage(condition, pageIndex, rows);
		List<StmasterSite> result = queryPage.getRows();
		List<StmasterSiteVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteVo.class, result);
		AdPager<StmasterSiteVo> resultVo = new AdPager<StmasterSiteVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterSite record) {
		if(record.getSiteId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterSite find = stmasterSiteDAO.find(record.getSiteId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterSiteDAO.update(find);
		
	}
}





