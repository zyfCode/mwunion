package com.sungan.ad.service.st;

import java.util.List;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.MuService;
import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.vo.st.StmasterPlatAccountVo;
import com.sungan.ad.vo.st.StmasterVo;

/**
 * 说明:
 */
public interface StmasterService extends  MuService{
	StmasterVo find(String id);


	/**
	 *查询几遍信息
	 * @param stmasterId
	 * @return
	 */
	StmasterPlatAccountVo queryPlateAccount(String stmasterId);

	/**
	 * 管理台添加站长
	 * @param record
	 * @return
	 */
	String insert(Stmaster record);
	/**
	 * 管理台添加站长
	 * @param record
	 * @return
	 */
	void insert(Stmaster record, StmasterSite site);

	/**
	 * 管理台删除站长
	 * @param id
	 */
	void delete(String id);

	/**
	 * 停用账号
	 * @param id
	 */
	void disable(String id);
	/**
	 * 黑名单
	 * @param id
	 */
	void blackList(String id);

	/**
	 * 注销账号
	 * @param id
	 */
	void cancel(String id);

	void update(Stmaster task);
	List<StmasterVo> queryList(Stmaster condition);
	AdPager<StmasterVo> queryPager(Stmaster condition,int pageIndex,int rows);

	/**
	 * 停用站长
	 * @param stmasterId
	 */
    void enable(String stmasterId);

	/**
	 * 黑名单列表
	 * @param record
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	AdPager<StmasterVo> queryBlackListPager(Stmaster record, Integer pageNo, Integer pageSize);
}
