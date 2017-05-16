package com.sungan.ad.service.adhost.impl;

import java.util.Date;
import java.util.List;

import com.sungan.ad.commons.service.event.EnumEventType;
import com.sungan.ad.commons.service.event.EvenContext;
import com.sungan.ad.commons.service.event.EventQueen;
import com.sungan.ad.dao.UserDAO;
import com.sungan.ad.dao.model.User;
import com.sungan.ad.dao.model.adenum.EnumUserStatus;
import com.sungan.ad.dao.model.adenum.EnumUserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.IdGeneratorFactory;
import com.sungan.ad.dao.AdHostDAO;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.parser.AnnotationParser;
import com.sungan.ad.service.adhost.AdHostService;
import com.sungan.ad.service.adhost.vo.AdHostVo;

/**
 * 说明:
 * 
 * @version V1.1
 */
@Service
public class AdHostServiceImpl implements AdHostService{
	
	@Autowired
	private AdHostDAO adHostDAO;
	@Autowired
	private UserDAO userDAO;

	public AdHostDAO getAdHostDAO() {
		return adHostDAO;
	}

	public void setAdHostDAO(AdHostDAO adHostDAO) {
		this.adHostDAO = adHostDAO;
	}

	@Override
	public String insert(AdHost record) {
		User user = new User();
		String userId = IdGeneratorFactory.nextId();
		user.setUserId(userId);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		user.setUserAcount(record.getUserAccount());
		user.setUserName(record.getAdhostName());
		int length = record.getMobile().length();
		String pwd = record.getMobile().substring(length - 6, length);
		user.setUserPwd(pwd);  //密码为手机后6位
		user.setUserStatus(EnumUserStatus.NORMAL.getKey());
		user.setUserType(EnumUserType.ADHOST.getKey());
		userDAO.insert(user);
		String nextId = IdGeneratorFactory.nextId();
		record.setAdhostId(nextId);
		record.setUserId(userId);
		record.setCreateTime(new Date());
		record.setUpdateTime(new Date());
		record.setUserName(record.getAdhostName());
		adHostDAO.insert(record);
		EvenContext context = new EvenContext();
		context.setTarget(record);
		EventQueen.addEvent(EnumEventType.ADD_ADHOST,context);
		return nextId;
	}

	@Override
	public List<AdHostVo> queryList(AdHost condition) {
		List<AdHost> query = (List<AdHost>) adHostDAO.query(condition);
		 List<AdHostVo> parseToVoList = AnnotationParser.parseToVoList(AdHostVo.class, query);
		return parseToVoList;
	}

		@Override
	public void delete(String id) {
		AdHost find = this.adHostDAO.find(id);
		if (find != null) {
			adHostDAO.delete(find);
		}
	}
	
	@Override
	public AdHostVo find(String id) {
		AdHost find = adHostDAO.find(id);
		if(find==null){
			return null;
		}
		AdHostVo parseToVo = AnnotationParser.parseToVo(AdHostVo.class, find);
		return parseToVo;
	}

		@Override
	public AdPager<AdHostVo> queryPager(AdHost condition, int pageIndex, int rows) {
		AdPager<AdHost> queryPage = adHostDAO.queryPage(condition, pageIndex, rows);
		List<AdHost> result = queryPage.getRows();
		List<AdHostVo> parseToVoList = AnnotationParser.parseToVoList(AdHostVo.class, result);
		AdPager<AdHostVo> resultVo = new AdPager<AdHostVo>(pageIndex, rows, queryPage.getTotal());
		resultVo.setRows(parseToVoList);
		return resultVo;
	}
	
	@Override
	public void update(AdHost record) {
		if(record.getAdhostId()==null){
			throw new AdRuntimeException("记录ID为空");
		}
		AdHost find = adHostDAO.find(record.getAdhostId());
		if(find==null){
			throw new AdRuntimeException("记录不存在");
		}
		try {
			AdCommonsUtil.beanCopyWithoutNull(record, find);
		} catch (Exception e) {
			throw new AdRuntimeException("参数异常");
		}
		find.setUpdateTime(new Date());
 		adHostDAO.update(find);
		
	}
}





