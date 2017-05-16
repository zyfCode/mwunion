package com.sungan.ad.service.st.impl;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.StmasterPlatAccountDAO;
import com.sungan.ad.dao.model.StmasterPlatAccount;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.st.StmasterPlatAccountService;
import com.sungan.ad.vo.st.StmasterPlatAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class StmasterPlatAccountServiceImpl implements StmasterPlatAccountService {
	
	@Autowired
	private StmasterPlatAccountDAO stmasterPlatAccountDAO;
	

	public StmasterPlatAccountDAO getStmasterPlatAccountDAO() {
		return stmasterPlatAccountDAO;
	}

	public void setStmasterPlatAccountDAO(StmasterPlatAccountDAO stmasterPlatAccountDAO) {
		this.stmasterPlatAccountDAO = stmasterPlatAccountDAO;
	}

	@Override
	public String insert(StmasterPlatAccount record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAccountId(nextId);
		Date date = new Date();
		record.setCreateTime(date);
		record.setUpdateTime(date);
		stmasterPlatAccountDAO.insert(record);
		return nextId;
	}

	@Override
	public List<StmasterPlatAccountVo> queryList(StmasterPlatAccount condition) {
		List<StmasterPlatAccount> query = (List<StmasterPlatAccount>) stmasterPlatAccountDAO.query(condition);
		 List<StmasterPlatAccountVo> parseToVoList = AnnotationParser.parseToVoList(StmasterPlatAccountVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		StmasterPlatAccount find = this.stmasterPlatAccountDAO.find(id);
		if (find != null) {
			stmasterPlatAccountDAO.delete(find);
		}
	}
	
	@Override
	public StmasterPlatAccountVo find(Long id) {
		StmasterPlatAccount find = stmasterPlatAccountDAO.find(id);
		if(find==null){
			return null;
		}
		StmasterPlatAccountVo parseToVo = AnnotationParser.parseToVo(StmasterPlatAccountVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<StmasterPlatAccountVo> queryPager(StmasterPlatAccount condition, int pageIndex, int rows) {
		AdPager<StmasterPlatAccount> queryPage = stmasterPlatAccountDAO.queryPage(condition, pageIndex, rows);
		List<StmasterPlatAccount> result = queryPage.getRows();
		List<StmasterPlatAccountVo> parseToVoList = AnnotationParser.parseToVoList(StmasterPlatAccountVo.class, result);
		AdPager<StmasterPlatAccountVo> resultVo = new AdPager<StmasterPlatAccountVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(StmasterPlatAccount record) {
		if(record.getAccountId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		StmasterPlatAccount find = stmasterPlatAccountDAO.find(record.getAccountId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		stmasterPlatAccountDAO.update(find);
		
	}
}





