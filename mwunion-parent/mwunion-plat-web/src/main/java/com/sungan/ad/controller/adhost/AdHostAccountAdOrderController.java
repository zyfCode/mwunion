package com.sungan.ad.controller.adhost;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdHostAccountAdOrderValid;
import com.sungan.ad.dao.model.AdHostAccountAdOrder;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhostaccountadorder")
public class AdHostAccountAdOrderController {
	@Autowired
	private AdHostAccountAdOrderService service;
	
	
	@RequestMapping("/deleteadhostaccountadorder")
	@ResponseBody
	public Object deleteadhostaccountadorder  (AdHostAccountAdOrder record){
		service.delete(record.getAdOrderId());
		return new AdResponse();
	}
	@RequestMapping("/addadhostaccountadorder")
	@ResponseBody
	public Object addadhostaccountadorder (@Valid AdHostAccountAdOrderValid record){
		AdHostAccountAdOrder w = new AdHostAccountAdOrder();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadhostaccountadorder")
	@ResponseBody
	public Object updateadhostaccountadorder(@Valid AdHostAccountAdOrderValid record){
		AdHostAccountAdOrder w = new AdHostAccountAdOrder();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listadhostaccountadorder.json")
	@ResponseBody
	public Object listTaskadhostaccountadorder(AdHostAccountAdOrder record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdHostAccountAdOrderVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}