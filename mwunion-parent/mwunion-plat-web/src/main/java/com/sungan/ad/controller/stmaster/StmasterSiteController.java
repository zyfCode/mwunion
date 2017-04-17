package com.sungan.ad.controller.stmaster;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.StmasterSiteValid;
import com.sungan.ad.dao.model.StmasterSite;
import com.sungan.ad.service.st.StmasterSiteService;
import com.sungan.ad.vo.st.StmasterSiteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 说明:
 */
@Controller
@RequestMapping("/stmastersite")
public class StmasterSiteController {
	@Autowired
	private StmasterSiteService service;


	@RequestMapping("/deletestmastersite")
	@ResponseBody
	public Object deletestmastersite  (StmasterSite record){
		service.delete(record.getSiteId());
		return new AdResponse();
	}
	@RequestMapping("/addstmastersite")
	@ResponseBody
	public Object addstmastersite (@Valid StmasterSiteValid record){
		StmasterSite w = new StmasterSite();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updatestmastersite")
	@ResponseBody
	public Object updatestmastersite(@Valid StmasterSiteValid record){
		StmasterSite w = new StmasterSite();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/liststmastersite.json")
	@ResponseBody
	public Object listTaskstmastersite(StmasterSite record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<StmasterSiteVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}