package com.sungan.ad.controller.user;
import javax.validation.Valid;

import com.sungan.ad.controller.validBean.StmasterSiteValid;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.vo.st.StmasterPlatAccountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.StmasterValid;
import com.sungan.ad.dao.model.Stmaster;
import com.sungan.ad.service.st.StmasterService;
import com.sungan.ad.vo.st.StmasterVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/stmaster")
public class StmasterController {
	@Autowired
	private StmasterService service;


	@RequestMapping("/stmasterinfo")
	@ResponseBody
	public Object stmasterInfo(String stmasterId){
		StmasterVo stmasterVo = service.find(stmasterId);
		return stmasterVo;
	}

	@RequestMapping("/plataccountinfo")
	@ResponseBody
	public Object queryPlatAccountInfo(String stmasterId){
		StmasterPlatAccountVo stmasterPlatAccountVo = service.queryPlateAccount(stmasterId);
		return stmasterPlatAccountVo;
	}
	
	@RequestMapping("/disablestmaster")
	@ResponseBody
	public Object disablestmaster  (String stmasterId){
		service.disable(stmasterId);
		return new AdResponse();
	}
	@RequestMapping("/enablestmaster")
	@ResponseBody
	public Object enablestmaster  (String stmasterId){
		service.enable(stmasterId);
		return new AdResponse();
	}
	@RequestMapping("/cancelstmaster")
	@ResponseBody
	public Object cancelstmaster  (String stmasterId){
		service.cancel(stmasterId);
		return new AdResponse();
	}
	@RequestMapping("/blackList")
	@ResponseBody
	public Object blackList  (String stmasterId){
		service.blackList(stmasterId);
		return new AdResponse();
	}
	@RequestMapping("/deletestmaster")
	@ResponseBody
	public Object deletestmaster  (Stmaster record){
		service.delete(record.getStmasterId());
		return new AdResponse();
	}
	@RequestMapping("/addstmaster")
	@ResponseBody
	public Object addstmaster (@Valid StmasterValid record, @Valid StmasterSiteValid site){
		Stmaster w = new Stmaster();
		AdCommonsUtil.copyProperties(w, record);
		StmasterSite siteRecord = new StmasterSite();
		AdCommonsUtil.copyProperties(siteRecord, site);
		service.insert(w,siteRecord);
		return new AdResponse();
	}
	
	@RequestMapping("/updatestmaster")
	@ResponseBody
	public Object updatestmaster(@Valid StmasterValid record){
		Stmaster w = new Stmaster();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/liststmaster.json")
	@ResponseBody
	public Object listTaskstmaster(Stmaster record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<StmasterVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
	@RequestMapping("/showblacklist.json")
	@ResponseBody
	public Object showblacklist(Stmaster record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<StmasterVo> queryPager = service.queryBlackListPager(record, pageNo, pageSize);
		return queryPager;
	}
}