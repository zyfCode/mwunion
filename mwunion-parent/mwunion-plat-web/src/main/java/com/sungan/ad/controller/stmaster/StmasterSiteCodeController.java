package com.sungan.ad.controller.stmaster;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.StmasterSiteCodeValid;
import com.sungan.ad.dao.model.StmasterSiteCode;
import com.sungan.ad.dao.model.adenum.EnumStmasterSiteCodeStatus;
import com.sungan.ad.service.st.StmasterSiteCodeService;
import com.sungan.ad.vo.st.StmasterSiteCodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 说明:
 */
@Controller
@RequestMapping("/stmastersitecode")
public class StmasterSiteCodeController {
	@Autowired
	private StmasterSiteCodeService service;
	
	
	@RequestMapping("/deletestmastersitecode")
	@ResponseBody
	public Object deletestmastersitecode  (StmasterSiteCode record){
		service.delete(record.getSiteCodeId());
		return new AdResponse();
	}

	@RequestMapping("/updatestmastersitecode")
	@ResponseBody
	public Object updatestmastersitecode(@Valid StmasterSiteCodeValid record){
		StmasterSiteCode w = new StmasterSiteCode();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}

	/**
	 * 禁用code
	 * @param siteCodeId
	 * @return
	 */
	@RequestMapping("/invalidcode")
	@ResponseBody
	public Object invalidcode(String siteCodeId){
		StmasterSiteCode w = new StmasterSiteCode();
		w.setSiteCodeId(siteCodeId);
		w.setCodeStatus(EnumStmasterSiteCodeStatus.INVALID.getKey());
		service.update(w);
		return new AdResponse();
	}

	/**
	 * CODE生效
	 * @param siteCodeId
	 * @return
	 */
	@RequestMapping("/inused")
	@ResponseBody
	public Object inused(String siteCodeId){
		StmasterSiteCode w = new StmasterSiteCode();
		w.setSiteCodeId(siteCodeId);
		w.setCodeStatus(EnumStmasterSiteCodeStatus.INUSED.getKey());
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/liststmastersitecode.json")
	@ResponseBody
	public Object listTaskstmastersitecode(StmasterSiteCode record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<StmasterSiteCodeVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}