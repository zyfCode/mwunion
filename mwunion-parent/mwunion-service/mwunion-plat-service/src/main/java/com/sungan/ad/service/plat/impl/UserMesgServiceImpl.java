package com.sungan.ad.service.plat.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.UserMesgDAO;
import com.sungan.ad.dao.model.UserMesg;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.plat.UserMesgService;
import com.sungan.ad.vo.UserMesgVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class UserMesgServiceImpl implements UserMesgService{
	
	@Autowired
	private UserMesgDAO userMesgDAO;
	

	public UserMesgDAO getUserMesgDAO() {
		return userMesgDAO;
	}

	public void setUserMesgDAO(UserMesgDAO userMesgDAO) {
		this.userMesgDAO = userMesgDAO;
	}

	@Override
	public String insert(UserMesg record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setMsgId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		userMesgDAO.insert(record);
		return nextId;
	}

	@Override
	public List<UserMesgVo> queryList(UserMesg condition) {
		List<UserMesg> query = (List<UserMesg>) userMesgDAO.query(condition);
		 List<UserMesgVo> parseToVoList = AnnotationParser.parseToVoList(UserMesgVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		UserMesg find = this.userMesgDAO.find(id);
		if (find != null) {
			userMesgDAO.delete(find);
		}
	}
	
	@Override
	public UserMesgVo find(String id) {
		UserMesg find = userMesgDAO.find(id);
		if(find==null){
			return null;
		}
		UserMesgVo parseToVo = AnnotationParser.parseToVo(UserMesgVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<UserMesgVo> queryPager(UserMesg condition, int pageIndex, int rows) {
		AdPager<UserMesg> queryPage = userMesgDAO.queryPage(condition, pageIndex, rows);
		List<UserMesg> result = queryPage.getRows();
		List<UserMesgVo> parseToVoList = AnnotationParser.parseToVoList(UserMesgVo.class, result);
		AdPager<UserMesgVo> resultVo = new AdPager<UserMesgVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(UserMesg record) {
		if(record.getMsgId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		UserMesg find = userMesgDAO.find(record.getMsgId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		userMesgDAO.update(find);
		
	}
}





