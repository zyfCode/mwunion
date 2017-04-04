package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostAccountDAO;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostAccountService;
import com.sungan.ad.service.adhost.vo.AdHostAccountVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostAccountServiceImpl implements AdHostAccountService{
	
	@Autowired
	private AdHostAccountDAO adHostAccountDAO;
	

	public AdHostAccountDAO getAdHostAccountDAO() {
		return adHostAccountDAO;
	}

	public void setAdHostAccountDAO(AdHostAccountDAO adHostAccountDAO) {
		this.adHostAccountDAO = adHostAccountDAO;
	}

	@Override
	public String insert(AdHostAccount record) {
		String nextId = IdGeneratorFactory.nextId();
		record.setAccountId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		adHostAccountDAO.insert(record);
		return nextId;
	}

	@Override
	public List<AdHostAccountVo> queryList(AdHostAccount condition) {
		List<AdHostAccount> query = (List<AdHostAccount>) adHostAccountDAO.query(condition);
		 List<AdHostAccountVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHostAccount find = this.adHostAccountDAO.find(id);
		if (find != null) {
			adHostAccountDAO.delete(find);
		}
	}
	
	@Override
	public AdHostAccountVo find(String id) {
		AdHostAccount find = adHostAccountDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostAccountVo parseToVo = AnnotationParser.parseToVo(AdHostAccountVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostAccountVo> queryPager(AdHostAccount condition, int pageIndex, int rows) {
		AdPager<AdHostAccount> queryPage = adHostAccountDAO.queryPage(condition, pageIndex, rows);
		List<AdHostAccount> result = queryPage.getRows();
		List<AdHostAccountVo> parseToVoList = AnnotationParser.parseToVoList(AdHostAccountVo.class, result);
		AdPager<AdHostAccountVo> resultVo = new AdPager<AdHostAccountVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHostAccount record) {
		if(record.getAccountId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHostAccount find = adHostAccountDAO.find(record.getAccountId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostAccountDAO.update(find);
		
	}
}





