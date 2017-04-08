package com.sungan.ad.controller.stmaster;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.StmasterAccountValid;
import com.sungan.ad.dao.model.StmasterAccount;
import com.sungan.ad.service.st.StmasterAccountService;
import com.sungan.ad.vo.st.StmasterAccountVo;

/**
 * 说明:
 */
@Controller
@RequestMapping("/stmasteraccount")
public class StmasterAccountController {
	@Autowired
	private StmasterAccountService service;
	
	
	@RequestMapping("/deletestmasteraccount")
	@ResponseBody
	public Object deletestmasteraccount  (StmasterAccount record){
		service.delete(record.getAccountId());
		return new AdResponse();
	}
	@RequestMapping("/addstmasteraccount")
	@ResponseBody
	public Object addstmasteraccount (@Valid StmasterAccountValid record){
		StmasterAccount w = new StmasterAccount();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updatestmasteraccount")
	@ResponseBody
	public Object updatestmasteraccount(@Valid StmasterAccountValid record){
		StmasterAccount w = new StmasterAccount();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/liststmasteraccount.json")
	@ResponseBody
	public Object listTaskstmasteraccount(StmasterAccount record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<StmasterAccountVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}