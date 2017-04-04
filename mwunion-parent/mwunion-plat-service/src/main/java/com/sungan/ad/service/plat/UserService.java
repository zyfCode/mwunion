package com.sungan.ad.service.plat;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.User;
import com.sungan.ad.vo.UserVo;

/**
 * 说明:
 */
public interface UserService extends  MuService{
	UserVo find(String id);
	String insert(User record);
	void delete(String id);
	void update(User task);
	List<UserVo> queryList(User condition);
	AdPager<UserVo> queryPager(User condition,int pageIndex,int rows);
}
