package com.sungan.ad.controller.user;

import javax.validation.Valid;

import com.sungan.ad.controller.validBean.view.AdHostOrder;
import com.sungan.ad.dao.model.AdHostAccount;
import com.sungan.ad.service.adhost.AdHostAccountService;
import com.sungan.ad.service.adhost.vo.AdHostAccountVo;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhost")
public class AdHostController {
	@Autowired
	private AdHostService service;
	@Autowired
	private AdHostAccountService adHostAccountService;
	
	@RequestMapping("/addmoney")
	@ResponseBody
	public Object addMoney(@Valid  AdHostOrder order){
		String adHostId = order.getAdhostId();
		String anaxNames = order.getAnaxNames();
	    BigDecimal moneyAmount = BigDecimal.valueOf(Double.valueOf(order.getMoneyAmount()));
		String remark = order.getRemark();
		List<String> files = new ArrayList<String>();
		if (StringUtils.isNotBlank(anaxNames)) {
			JSONArray jsonArray = JSONArray.fromObject(anaxNames);
			Object[] objects = jsonArray.toArray();
			for (Object obj:objects){
                files.add(obj.toString());
            }
		}
		adHostAccountService.addMoneyOrder(adHostId,moneyAmount,remark,files);
		return new AdResponse();
	}
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
	@RequestMapping("/adhostinfo")
	@ResponseBody
	public Object adhostinfo (String adhostId){
		AdHostVo adHostVo = service.find(adhostId);
		return adHostVo;
	}
	@RequestMapping("/adhostaccountinfo")
	@ResponseBody
	public Object adhostaccountinfo (String adhostId){
		AdHostAccount condition = new AdHostAccount();
		condition.setAdHostId(adhostId);
		List<AdHostAccountVo> adHostAccountVos = adHostAccountService.queryList(condition);
		if(adHostAccountVos!=null&&adHostAccountVos.size()>0){
			return adHostAccountVos.get(0);
		}
		return null;
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