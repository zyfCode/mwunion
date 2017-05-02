package com.sungan.ad.controller.plat;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.commons.AdResponse;
import com.sungan.ad.controller.validBean.PlatProductValid;
import com.sungan.ad.dao.model.PlatProduct;
import com.sungan.ad.service.plat.PlatProductService;
import com.sungan.ad.vo.PlatProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * 说明:
 */
@Controller
@RequestMapping("/platproduct")
public class PlatProductController {
	@Autowired
	private PlatProductService service;
	
	
	@RequestMapping("/deleteplatproduct")
	@ResponseBody
	public Object deleteplatproduct  (PlatProduct record){
		service.delete(record.getProductId());
		return new AdResponse();
	}
	@RequestMapping("/pubicproduct")
	@ResponseBody
	public Object pubicproduct  (PlatProduct record){
		service.pubicproduct(record.getProductId());
		return new AdResponse();
	}
	@RequestMapping("/downshelfproduct")
	@ResponseBody
	public Object downshelfproduct  (PlatProduct record){
		service.downShelfProduct(record.getProductId());
		return new AdResponse();
	}
	@RequestMapping("/addplatproduct")
	@ResponseBody
	public Object addplatproduct (@Valid PlatProductValid record){
		PlatProduct w = new PlatProduct();
		AdCommonsUtil.copyProperties(w, record);
		service.insert(w);
		return new AdResponse();
	}
	
	@RequestMapping("/updateplatproduct")
	@ResponseBody
	public Object updateplatproduct(@Valid PlatProductValid record){
		PlatProduct w = new PlatProduct();
		AdCommonsUtil.copyProperties(w, record);
		service.update(w);
		return new AdResponse();
	}
	@RequestMapping("/listplatproduct.json")
	@ResponseBody
	public Object listTaskplatproduct(PlatProduct record,Integer pageNo,Integer pageSize){
		if(record!=null){
			AdCommonsUtil.proStrEmpytToNull(record);
		}
		AdPager<PlatProductVo> queryPager = service.queryPager(record, pageNo, pageSize);
		return queryPager;
	}
}