package com.sungan.ad.service.plat;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.UserMesg;
import com.sungan.ad.vo.UserMesgVo;

/**
 * 说明:
 */
public interface UserMesgService extends  MuService{
	UserMesgVo find(String id);
	String insert(UserMesg record);
	void delete(String id);
	void update(UserMesg task);
	List<UserMesgVo> queryList(UserMesg condition);
	AdPager<UserMesgVo> queryPager(UserMesg condition,int pageIndex,int rows);
}
