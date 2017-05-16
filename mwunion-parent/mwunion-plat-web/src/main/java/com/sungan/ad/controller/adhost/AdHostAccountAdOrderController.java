package com.sungan.ad.controller.adhost;

import javax.validation.Valid;

import com.sungan.ad.controller.validBean.AdHostAccountAdOrderAttriValid;
import com.sungan.ad.controller.validBean.view.AdHostAccountAddForm;
import com.sungan.ad.dao.model.AdHostAccountAdOrderAttri;
import com.sungan.ad.dao.model.AdHostAccountAdOrderSources;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderAttriService;
import com.sungan.ad.service.adhost.AdHostAccountAdOrderSourcesService;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderAttriVo;
import com.sungan.ad.service.adhost.vo.AdHostAccountAdOrderSourcesVo;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
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

import java.math.BigDecimal;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 */
@Controller
@RequestMapping("/adhostaccountadorder")
public class AdHostAccountAdOrderController {
	@Autowired
	private AdHostAccountAdOrderService service;
	@Autowired
	private AdHostAccountAdOrderAttriService attriService;

	@Autowired
	private AdHostAccountAdOrderSourcesService sourcesService;

	@RequestMapping("/listadhostaccountadordersources.json")
	@ResponseBody
	public Object listTaskadhostaccountadordersources(AdHostAccountAdOrderSources record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<AdHostAccountAdOrderSourcesVo> queryPager = sourcesService.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
	@RequestMapping("/deleteadhostaccountadorder")
	@ResponseBody
	public Object deleteadhostaccountadorder  (AdHostAccountAdOrder record){
		service.delete(record.getAdOrderId());
		return new AdResponse();
	}
	@RequestMapping("/comfireOrder")
	@ResponseBody
	public Object comfireOrder  (AdHostAccountAdOrder record){
		System.out.println("bbbbbbb");
		service.comfireOrder(record.getAdOrderId());
		return new AdResponse();
	}

	@RequestMapping("/stoporder")
	@ResponseBody
	public Object stopOrder  (AdHostAccountAdOrder record){
		service.stopOrder(record.getAdOrderId());
		return new AdResponse();
	}
	@RequestMapping("/orderinfo")
	@ResponseBody
	public Object orderInfo  (AdHostAccountAdOrder record){
		AdHostAccountAdOrderAttri conditon = new AdHostAccountAdOrderAttri();
		conditon.setAdOrderId(record.getAdOrderId());
		List<AdHostAccountAdOrderAttriVo> adHostAccountAdOrderAttriVos = attriService.queryList(conditon);
		if(adHostAccountAdOrderAttriVos!=null&&adHostAccountAdOrderAttriVos.size()>0){
			AdHostAccountAdOrderAttriVo adHostAccountAdOrderAttriVo = adHostAccountAdOrderAttriVos.get(0);
			return adHostAccountAdOrderAttriVo;
		}
		return new AdResponse();
	}
	@RequestMapping("/enable")
	@ResponseBody
	public Object enable  (AdHostAccountAdOrder record){
		service.enable(record.getAdOrderId());
		return new AdResponse();
	}
	@RequestMapping("/invalidorder")
	@ResponseBody
	public Object invalidOrder (AdHostAccountAdOrder record){
		service.invalidOrder(record.getAdOrderId());
		return new AdResponse();
	}
	@RequestMapping("/addadhostaccountadorder")
	@ResponseBody
	public Object addadhostaccountadorder (@Valid AdHostAccountAddForm formData){
		AdHostAccountAdOrder w = new AdHostAccountAdOrder();
		w.setVersion(0);
		w.setProductId(formData.getProductId());
		w.setAdHostId(formData.getAdhostId());
		w.setPayType(formData.getPayType());
		AdHostAccountAdOrderAttri orderAttri = new AdHostAccountAdOrderAttri();
		orderAttri.setAdHostId(formData.getAdhostId());
		String adAmount = formData.getAdAmount();
		if(StringUtils.isBlank(adAmount)){
			adAmount = "0";
		}
		List<String> files = new ArrayList<String>();
		if(StringUtils.isNotBlank(formData.getAnaxNames())) {
			JSONArray jsonArray = JSONArray.fromObject(formData.getAnaxNames());
			Object[] objects = jsonArray.toArray();
			for (Object obj:objects){
				files.add(obj.toString());
			}
		}
		BigDecimal bigDecimal = new BigDecimal(Double.valueOf(adAmount));
		orderAttri.setAdAmount(bigDecimal);
		service.insert(w,orderAttri,files);
		return new AdResponse();
	}
	
	@RequestMapping("/updateadhostaccountadorder")
	@ResponseBody
	public Object updateadhostaccountadorder(@Valid AdHostAccountAdOrderValid record){
		AdHostAccountAdOrder w = new AdHostAccountAdOrder();
		AdCommonsUtil.copyProperties(w, record);
		AdHostAccountAdOrderAttri attri = new AdHostAccountAdOrderAttri();
		AdCommonsUtil.copyProperties(attri, record);
		service.update(w,attri);
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