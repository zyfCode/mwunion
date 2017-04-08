package com.sungan.ad.controller.adhost;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdHostAccountValid;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.service.adhost.AdHostAccountService;
import com.sungan.ad.service.adhost.vo.AdHostAccountVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhostaccount")
public class AdHostAccountController {
	@Autowired
	private AdHostAccountService service;
	
	
	@RequestMapping("/deleteadhostaccount")
	@ResponseBody
	public Object deleteadhostaccount  (AdHostAccount record){
		service.delete(record.getAccountId());
		return new AdResponse();
	}
	@RequestMapping("/addadhostaccount")
	@ResponseBody
	public Object addadhostaccount (@Valid AdHostAccountValid record){
		AdHostAccount w = new AdHostAccount();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadhostaccount")
	@ResponseBody
	public Object updateadhostaccount(@Valid AdHostAccountValid record){
		AdHostAccount w = new AdHostAccount();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listadhostaccount.json")
	@ResponseBody
	public Object listTaskadhostaccount(AdHostAccount record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdHostAccountVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}