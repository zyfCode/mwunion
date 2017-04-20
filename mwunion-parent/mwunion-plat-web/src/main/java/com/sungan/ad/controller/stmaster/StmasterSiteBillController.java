package com.sungan.ad.controller.stmaster;

import javax.validation.Valid;

import com.sungan.ad.controller.validBean.view.StSettleBillVlid;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.StmasterSiteBillValid;
import com.sungan.ad.dao.model.StmasterSiteBill;
import com.sungan.ad.service.st.StmasterSiteBillService;
import com.sungan.ad.vo.st.StmasterSiteBillVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 */
@Controller
@RequestMapping("/stmastersitebill")
public class StmasterSiteBillController {
	@Autowired
	private StmasterSiteBillService service;


	@RequestMapping("/settleBill")
	@ResponseBody
	public Object settleBill(@Valid StSettleBillVlid dataBean){
		String settlAmount = dataBean.getSettlAmount();
		BigDecimal bigDecimal = BigDecimal.valueOf(Double.valueOf(settlAmount));
		List<String> files = new ArrayList<String>();
		if(StringUtils.isNotBlank(dataBean.getAnaxNames())) {
			JSONArray jsonArray = JSONArray.fromObject(dataBean.getAnaxNames());
			Object[] objects = jsonArray.toArray();
			for (Object obj:objects){
				files.add(obj.toString());
			}
		}
		service.settleBill(dataBean.getStBillId(),bigDecimal,files);
		return new AdResponse();
	}
	
	@RequestMapping("/deletestmastersitebill")
	@ResponseBody
	public Object deletestmastersitebill  (StmasterSiteBill record){
		service.delete(record.getStBillId());
		return new AdResponse();
	}
	@RequestMapping("/addstmastersitebill")
	@ResponseBody
	public Object addstmastersitebill (@Valid StmasterSiteBillValid record){
		StmasterSiteBill w = new StmasterSiteBill();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updatestmastersitebill")
	@ResponseBody
	public Object updatestmastersitebill(@Valid StmasterSiteBillValid record){
		StmasterSiteBill w = new StmasterSiteBill();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/liststmastersitebill.json")
	@ResponseBody
	public Object listTaskstmastersitebill(StmasterSiteBill record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<StmasterSiteBillVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}