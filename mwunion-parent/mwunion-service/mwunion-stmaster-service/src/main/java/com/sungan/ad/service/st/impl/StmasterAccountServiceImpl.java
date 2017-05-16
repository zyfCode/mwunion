package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterAccountDAO;
import com.sungan.ad.dao.model.StmasterAccount;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterAccountService;
import com.sungan.ad.vo.st.StmasterAccountVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterAccountServiceImpl implements StmasterAccountService{
	
	@Autowired
	private StmasterAccountDAO stmasterAccountDAO;
	

	public StmasterAccountDAO getStmasterAccountDAO() {
		return stmasterAccountDAO;
	}

	public void setStmasterAccountDAO(StmasterAccountDAO stmasterAccountDAO) {
		this.stmasterAccountDAO = stmasterAccountDAO;
	}

	@Override
	public String insert(StmasterAccount record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAccountId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		stmasterAccountDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterAccountVo> queryList(StmasterAccount condition) {
		List<StmasterAccount> query = (List<StmasterAccount>) stmasterAccountDAO.query(condition);
		 List<StmasterAccountVo> parseToVoList = AnnotationParser.parseToVoList(StmasterAccountVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		StmasterAccount find = this.stmasterAccountDAO.find(id);
		if (find != null) {
			stmasterAccountDAO.delete(find);
		}
	}
	
	@Override
	public StmasterAccountVo find(String id) {
		StmasterAccount find = stmasterAccountDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterAccountVo parseToVo = AnnotationParser.parseToVo(StmasterAccountVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterAccountVo> queryPager(StmasterAccount condition, int pageIndex, int rows) {
		AdPager<StmasterAccount> queryPage = stmasterAccountDAO.queryPage(condition, pageIndex, rows);
		List<StmasterAccount> result = queryPage.getRows();
		List<StmasterAccountVo> parseToVoList = AnnotationParser.parseToVoList(StmasterAccountVo.class, result);
		AdPager<StmasterAccountVo> resultVo = new AdPager<StmasterAccountVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterAccount record) {
		if(record.getAccountId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterAccount find = stmasterAccountDAO.find(record.getAccountId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterAccountDAO.update(find);
		
	}
}





