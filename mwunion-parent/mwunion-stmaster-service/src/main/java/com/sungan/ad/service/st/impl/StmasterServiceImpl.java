package com.sungan.ad.service.st.impl;

import java.util.Date;
import java.util.List;

import com.sungan.ad.dao.UserDAO;
import com.sungan.ad.dao.model.User;
import com.sungan.ad.dao.model.adenum.EnumUserStatus;
import com.sungan.ad.dao.model.adenum.EnumUserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory.getLogger(StmasterServiceImpl.class);
	@Autowired
	private StmasterDAO stmasterDAO;

	@Autowired
	private UserDAO userDAO;

	public StmasterDAO getStmasterDAO() {
		return stmasterDAO;
	}

	public void setStmasterDAO(StmasterDAO stmasterDAO) {
		this.stmasterDAO = stmasterDAO;
	}

	@Override
	public String insert(Stmaster record) {
		String nextId = null;
		try {
			User user = new User();
			String userId = IdGeneratorFactory.nextId();
			user.setUserId(userId);
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setUserAcount(record.getUserAccount());
			user.setUserName(record.getRecName());
			int length = record.getMobile().length();
			String pwd = record.getMobile().substring(length - 6, length);
			user.setUserPwd(pwd);  //密码为手机后6位
			user.setUserStatus(EnumUserStatus.NORMAL.getKey());
			user.setUserType(EnumUserType.ADHOST.getKey());
			userDAO.insert(user);

			nextId = IdGeneratorFactory.nextId();
			//record.setId(nextId);
			record.setUserId(userId);
			record.setStmasterId(nextId);
			record.setCreateTime(new Date());
			record.setUpdateTime(new Date());
			stmasterDAO.insert(record);
		} catch (Exception e) {
			logger.error("添加站长异常",e);
			throw new AdRuntimeException("添加站长异常");
		}
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
			String userId = find.getUserId();
			User user = userDAO.find(userId);
			if(user!=null) {
				user.setUserStatus(EnumUserStatus.CANCEL.getKey());
				userDAO.delete(user);
			}
			stmasterDAO.delete(find);
		}
	}
	@Override
	public void cancel(String id) {
		Stmaster find = this.stmasterDAO.find(id);
		if (find != null) {
			String userId = find.getUserId();
			User user = userDAO.find(userId);
			if(user!=null) {
				user.setUserStatus(EnumUserStatus.CANCEL.getKey());
				userDAO.update(user);
			}
			find.setUserStatus(EnumUserStatus.CANCEL.getKey());
			stmasterDAO.update(find);
		}
	}
	@Override
	public void disable(String id) {
		Stmaster find = this.stmasterDAO.find(id);
		if (find != null) {
			String userId = find.getUserId();
			User user = userDAO.find(userId);
			if(user!=null) {
				user.setUserStatus(EnumUserStatus.DISABLE.getKey());
				userDAO.update(user);
			}
			find.setUserStatus(EnumUserStatus.DISABLE.getKey());
			stmasterDAO.update(find);
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

		//注销状态的站长不允许修改
		EnumUserStatus status = EnumUserStatus.match(find.getUserStatus());
		if(status==EnumUserStatus.CANCEL){
			throw new AdRuntimeException("用户已经被注销");
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





