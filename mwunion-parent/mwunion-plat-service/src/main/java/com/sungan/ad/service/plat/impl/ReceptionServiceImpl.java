package com.sungan.ad.service.plat.impl;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.ReceptionDAO;
import com.sungan.ad.dao.UserDAO;
import com.sungan.ad.dao.model.Reception;
import com.sungan.ad.dao.model.User;
import com.sungan.ad.dao.model.adenum.EnumUserStatus;
import com.sungan.ad.dao.model.adenum.EnumUserType;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.plat.ReceptionService;
import com.sungan.ad.vo.ReceptionVo;
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
public class ReceptionServiceImpl implements ReceptionService{
	
	@Autowired
	private ReceptionDAO receptionDAO;

	@Autowired
	private UserDAO userDAO;

	public ReceptionDAO getReceptionDAO() {
		return receptionDAO;
	}

	public void setReceptionDAO(ReceptionDAO receptionDAO) {
		this.receptionDAO = receptionDAO;
	}

	@Override
	public String insert(Reception record) {
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
		user.setUserType(EnumUserType.RECEPTION.getKey());
		userDAO.insert(user);
		String nextId = IdGeneratorFactory.nextId();
		record.setUserId(userId);
		record.setRecId(nextId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		receptionDAO.insert(record);
		return nextId;
	}

	@Override
	public List<ReceptionVo> queryList(Reception condition) {
		List<Reception> query = (List<Reception>) receptionDAO.query(condition);
		 List<ReceptionVo> parseToVoList = AnnotationParser.parseToVoList(ReceptionVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		Reception find = this.receptionDAO.find(id);
		if (find != null) {
			String userId = find.getUserId();
			User user = userDAO.find(userId);
			if(user!=null){
				user.delete(user);
			}
			receptionDAO.delete(find);
		}
	}
	
	@Override
	public ReceptionVo find(String id) {
		Reception find = receptionDAO.find(id);
		if(find==null){
			return null;
		}
		ReceptionVo parseToVo = AnnotationParser.parseToVo(ReceptionVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<ReceptionVo> queryPager(Reception condition, int pageIndex, int rows) {
		AdPager<Reception> queryPage = receptionDAO.queryPage(condition, pageIndex, rows);
		List<Reception> result = queryPage.getRows();
		List<ReceptionVo> parseToVoList = AnnotationParser.parseToVoList(ReceptionVo.class, result);
		AdPager<ReceptionVo> resultVo = new AdPager<ReceptionVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(Reception record) {
		if(record.getRecId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		Reception find = receptionDAO.find(record.getRecId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		receptionDAO.update(find);
		
	}
}





