package com.sungan.ad.controller.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdHostValid;
import com.sungan.ad.dao.model.AdHost;
import com.sungan.ad.service.adhost.AdHostService;
import com.sungan.ad.service.adhost.vo.AdHostVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhost")
public class AdHostController {
	@Autowired
	private AdHostService service;
	
	
	@RequestMapping("/deleteadhost")
	@ResponseBody
	public Object deleteadhost  (AdHost record){
		service.delete(record.getAdhostId());
		return new AdResponse();
	}
	@RequestMapping("/addadhost")
	@ResponseBody
	public Object addadhost (@Valid AdHostValid record){
		AdHost w = new AdHost();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadhost")
	@ResponseBody
	public Object updateadhost(@Valid AdHostValid record){
		AdHost w = new AdHost();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listadhost.json")
	@ResponseBody
	public Object listTaskadhost(AdHost record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdHostVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}