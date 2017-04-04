package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterSiteCodeDAO;
import com.sungan.ad.dao.model.StmasterSiteCode;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterSiteCodeService;
import com.sungan.ad.vo.st.StmasterSiteCodeVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterSiteCodeServiceImpl implements StmasterSiteCodeService{
	
	@Autowired
	private StmasterSiteCodeDAO stmasterSiteCodeDAO;
	

	public StmasterSiteCodeDAO getStmasterSiteCodeDAO() {
		return stmasterSiteCodeDAO;
	}

	public void setStmasterSiteCodeDAO(StmasterSiteCodeDAO stmasterSiteCodeDAO) {
		this.stmasterSiteCodeDAO = stmasterSiteCodeDAO;
	}

	@Override
	public String insert(StmasterSiteCode record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setSiteCodeId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterSiteCodeDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterSiteCodeVo> queryList(StmasterSiteCode condition) {
		List<StmasterSiteCode> query = (List<StmasterSiteCode>) stmasterSiteCodeDAO.query(condition);
		 List<StmasterSiteCodeVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteCodeVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		StmasterSiteCode find = this.stmasterSiteCodeDAO.find(id);
		if (find != null) {
			stmasterSiteCodeDAO.delete(find);
		}
	}
	
	@Override
	public StmasterSiteCodeVo find(String id) {
		StmasterSiteCode find = stmasterSiteCodeDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterSiteCodeVo parseToVo = AnnotationParser.parseToVo(StmasterSiteCodeVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterSiteCodeVo> queryPager(StmasterSiteCode condition, int pageIndex, int rows) {
		AdPager<StmasterSiteCode> queryPage = stmasterSiteCodeDAO.queryPage(condition, pageIndex, rows);
		List<StmasterSiteCode> result = queryPage.getRows();
		List<StmasterSiteCodeVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteCodeVo.class, result);
		AdPager<StmasterSiteCodeVo> resultVo = new AdPager<StmasterSiteCodeVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterSiteCode record) {
		if(record.getSiteCodeId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterSiteCode find = stmasterSiteCodeDAO.find(record.getSiteCodeId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterSiteCodeDAO.update(find);
		
	}
}





