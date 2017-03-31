package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterSiteBillAnnexesDAO;
import com.sungan.ad.dao.model.StmasterSiteBillAnnexes;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterSiteBillAnnexesService;
import com.sungan.ad.vo.st.StmasterSiteBillAnnexesVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterSiteBillAnnexesServiceImpl implements StmasterSiteBillAnnexesService{
	
	@Autowired
	private StmasterSiteBillAnnexesDAO stmasterSiteBillAnnexesDAO;
	

	public StmasterSiteBillAnnexesDAO getStmasterSiteBillAnnexesDAO() {
		return stmasterSiteBillAnnexesDAO;
	}

	public void setStmasterSiteBillAnnexesDAO(StmasterSiteBillAnnexesDAO stmasterSiteBillAnnexesDAO) {
		this.stmasterSiteBillAnnexesDAO = stmasterSiteBillAnnexesDAO;
	}

	@Override
	public String insert(StmasterSiteBillAnnexes record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterSiteBillAnnexesDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterSiteBillAnnexesVo> queryList(StmasterSiteBillAnnexes condition) {
		List<StmasterSiteBillAnnexes> query = (List<StmasterSiteBillAnnexes>) stmasterSiteBillAnnexesDAO.query(condition);
		 List<StmasterSiteBillAnnexesVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteBillAnnexesVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		StmasterSiteBillAnnexes find = this.stmasterSiteBillAnnexesDAO.find(id);
		if (find != null) {
			stmasterSiteBillAnnexesDAO.delete(find);
		}
	}
	
	@Override
	public StmasterSiteBillAnnexesVo find(Long id) {
		StmasterSiteBillAnnexes find = stmasterSiteBillAnnexesDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterSiteBillAnnexesVo parseToVo = AnnotationParser.parseToVo(StmasterSiteBillAnnexesVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterSiteBillAnnexesVo> queryPager(StmasterSiteBillAnnexes condition, int pageIndex, int rows) {
		AdPager<StmasterSiteBillAnnexes> queryPage = stmasterSiteBillAnnexesDAO.queryPage(condition, pageIndex, rows);
		List<StmasterSiteBillAnnexes> result = queryPage.getRows();
		List<StmasterSiteBillAnnexesVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteBillAnnexesVo.class, result);
		AdPager<StmasterSiteBillAnnexesVo> resultVo = new AdPager<StmasterSiteBillAnnexesVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterSiteBillAnnexes record) {
		if(record.getId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterSiteBillAnnexes find = stmasterSiteBillAnnexesDAO.find(record.getId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterSiteBillAnnexesDAO.update(find);
		
	}
}





