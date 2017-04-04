package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterSiteBillDAO;
import com.sungan.ad.dao.model.StmasterSiteBill;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterSiteBillService;
import com.sungan.ad.vo.st.StmasterSiteBillVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterSiteBillServiceImpl implements StmasterSiteBillService{
	
	@Autowired
	private StmasterSiteBillDAO stmasterSiteBillDAO;
	

	public StmasterSiteBillDAO getStmasterSiteBillDAO() {
		return stmasterSiteBillDAO;
	}

	public void setStmasterSiteBillDAO(StmasterSiteBillDAO stmasterSiteBillDAO) {
		this.stmasterSiteBillDAO = stmasterSiteBillDAO;
	}

	@Override
	public String insert(StmasterSiteBill record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setStBillId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterSiteBillDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterSiteBillVo> queryList(StmasterSiteBill condition) {
		List<StmasterSiteBill> query = (List<StmasterSiteBill>) stmasterSiteBillDAO.query(condition);
		 List<StmasterSiteBillVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteBillVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		StmasterSiteBill find = this.stmasterSiteBillDAO.find(id);
		if (find != null) {
			stmasterSiteBillDAO.delete(find);
		}
	}
	
	@Override
	public StmasterSiteBillVo find(String id) {
		StmasterSiteBill find = stmasterSiteBillDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterSiteBillVo parseToVo = AnnotationParser.parseToVo(StmasterSiteBillVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterSiteBillVo> queryPager(StmasterSiteBill condition, int pageIndex, int rows) {
		AdPager<StmasterSiteBill> queryPage = stmasterSiteBillDAO.queryPage(condition, pageIndex, rows);
		List<StmasterSiteBill> result = queryPage.getRows();
		List<StmasterSiteBillVo> parseToVoList = AnnotationParser.parseToVoList(StmasterSiteBillVo.class, result);
		AdPager<StmasterSiteBillVo> resultVo = new AdPager<StmasterSiteBillVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterSiteBill record) {
		if(record.getStBillId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterSiteBill find = stmasterSiteBillDAO.find(record.getStBillId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterSiteBillDAO.update(find);
		
	}
}





