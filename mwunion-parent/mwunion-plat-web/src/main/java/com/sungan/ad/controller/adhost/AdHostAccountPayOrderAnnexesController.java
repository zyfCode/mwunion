package com.sungan.ad.controller.adhost;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.dao.model.AdHostAccountPayOrderAnnexes;
import com.sungan.ad.service.adhost.AdHostAccountPayOrderAnnexesService;
import com.sungan.ad.service.adhost.vo.AdHostAccountPayOrderAnnexesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhostaccountpayorderannexes")
public class AdHostAccountPayOrderAnnexesController {
	@Autowired
	private AdHostAccountPayOrderAnnexesService service;
	
	
	@RequestMapping("/deleteadhostaccountpayorderannexes")
	@ResponseBody
	public Object deleteadhostaccountpayorderannexes  (AdHostAccountPayOrderAnnexes record){
		service.delete(record.getAnnxId());
		return new AdResponse();
	}

	@RequestMapping("/listadhostaccountpayorderannexes.json")
	@ResponseBody
	public Object listTaskadhostaccountpayorderannexes(AdHostAccountPayOrderAnnexes record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdHostAccountPayOrderAnnexesVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}