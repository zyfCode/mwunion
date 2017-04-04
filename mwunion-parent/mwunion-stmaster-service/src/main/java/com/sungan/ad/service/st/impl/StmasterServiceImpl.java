package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterDAO;
import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterService;
import com.sungan.ad.vo.st.StmasterVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterServiceImpl implements StmasterService{
	
	@Autowired
	private StmasterDAO stmasterDAO;
	

	public StmasterDAO getStmasterDAO() {
		return stmasterDAO;
	}

	public void setStmasterDAO(StmasterDAO stmasterDAO) {
		this.stmasterDAO = stmasterDAO;
	}

	@Override
	public String insert(Stmaster record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setStmasterId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterVo> queryList(Stmaster condition) {
		List<Stmaster> query = (List<Stmaster>) stmasterDAO.query(condition);
		 List<StmasterVo> parseToVoList = AnnotationParser.parseToVoList(StmasterVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		Stmaster find = this.stmasterDAO.find(id);
		if (find != null) {
			stmasterDAO.delete(find);
		}
	}
	
	@Override
	public StmasterVo find(String id) {
		Stmaster find = stmasterDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterVo parseToVo = AnnotationParser.parseToVo(StmasterVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterVo> queryPager(Stmaster condition, int pageIndex, int rows) {
		AdPager<Stmaster> queryPage = stmasterDAO.queryPage(condition, pageIndex, rows);
		List<Stmaster> result = queryPage.getRows();
		List<StmasterVo> parseToVoList = AnnotationParser.parseToVoList(StmasterVo.class, result);
		AdPager<StmasterVo> resultVo = new AdPager<StmasterVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(Stmaster record) {
		if(record.getStmasterId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		Stmaster find = stmasterDAO.find(record.getStmasterId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterDAO.update(find);
		
	}
}





