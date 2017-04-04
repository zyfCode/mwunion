package com.sungan.ad.controller.user;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.ReceptionValid;
import com.sungan.ad.dao.model.Reception;
import com.sungan.ad.service.plat.ReceptionService;
import com.sungan.ad.vo.ReceptionVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/reception")
public class ReceptionController {
	@Autowired
	private ReceptionService service;
	
	
	@RequestMapping("/deletereception")
	@ResponseBody
	public Object deletereception  (Reception record){
		service.delete(record.getRecId());
		return new AdResponse();
	}
	@RequestMapping("/addreception")
	@ResponseBody
	public Object addreception (@Valid ReceptionValid record){
		Reception w = new Reception();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updatereception")
	@ResponseBody
	public Object updatereception(@Valid ReceptionValid record){
		Reception w = new Reception();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listreception.json")
	@ResponseBody
	public Object listTaskreception(Reception record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<ReceptionVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}