package com.sungan.ad.controller.adhost;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.AdHostAccountPayOrderValid;
import com.sungan.ad.dao.model.AdHostAccountPayOrder;
import com.sungan.ad.service.adhost.AdHostAccountPayOrderService;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhostaccountpayorder")
public class AdHostAccountPayOrderController {
	@Autowired
	private AdHostAccountPayOrderService service;
	
	
	@RequestMapping("/deleteadhostaccountpayorder")
	@ResponseBody
	public Object deleteadhostaccountpayorder  (AdHostAccountPayOrder record){
		service.delete(record.getPayOrderId());
		return new AdResponse();
	}
	@RequestMapping("/addadhostaccountpayorder")
	@ResponseBody
	public Object addadhostaccountpayorder (@Valid AdHostAccountPayOrderValid record){
		AdHostAccountPayOrder w = new AdHostAccountPayOrder();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadhostaccountpayorder")
	@ResponseBody
	public Object updateadhostaccountpayorder(@Valid AdHostAccountPayOrderValid record){
		AdHostAccountPayOrder w = new AdHostAccountPayOrder();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listadhostaccountpayorder.json")
	@ResponseBody
	public Object listTaskadhostaccountpayorder(AdHostAccountPayOrder record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdHostAccountPayOrderVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}