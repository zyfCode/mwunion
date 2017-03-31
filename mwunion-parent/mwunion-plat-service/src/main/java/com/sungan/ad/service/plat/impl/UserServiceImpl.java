package com.sungan.ad.service.plat.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.UserDAO;
import com.sungan.ad.dao.model.User;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.plat.UserService;
import com.sungan.ad.vo.UserVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDAO userDAO;
	

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public String insert(User record) {
		String nextId = IdGeneratorFactory.nextId();
		//record.setId(nextId);
		record.setUserId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		userDAO.insert(record);
		return nextId;
	}

	@Override
	public List<UserVo> queryList(User condition) {
		List<User> query = (List<User>) userDAO.query(condition);
		 List<UserVo> parseToVoList = AnnotationParser.parseToVoList(UserVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(Long id) {
		User find = this.userDAO.find(id);
		if (find != null) {
			userDAO.delete(find);
		}
	}
	
	@Override
	public UserVo find(Long id) {
		User find = userDAO.find(id);
		if(find==null){
			return null;
		}
		UserVo parseToVo = AnnotationParser.parseToVo(UserVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<UserVo> queryPager(User condition, int pageIndex, int rows) {
		AdPager<User> queryPage = userDAO.queryPage(condition, pageIndex, rows);
		List<User> result = queryPage.getRows();
		List<UserVo> parseToVoList = AnnotationParser.parseToVoList(UserVo.class, result);
		AdPager<UserVo> resultVo = new AdPager<UserVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(User record) {
		if(record.getUserId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		User find = userDAO.find(record.getUserId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		userDAO.update(find);
		
	}
}





