package com.sungan.ad.dao.model.adenum;

import com.sungan.ad.common.dict.DictHandlerImpl;
import com.sungan.ad.common.dict.EnumCommon;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.exception.AdRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月26日
 */
public enum AdHostAccountAdOrderHourStatus{
	UNCLEAR("0","未清算 "),CLEAR("1","已清算 ");
	public static final String DICT_KEY="ADORDER_HOURSTATUS";
	public static final String DICT_NAME="小时账单结算状态";
	private String key;
	private String label;
	
	
	private AdHostAccountAdOrderHourStatus(String key, String label) {
		this.key = key;
		this.label = label;
		try {
			Class.forName(EnumCommonImpl.class.getName());
		} catch (Exception e) {
			throw new AdRuntimeException("",e);
		}
	}
	public static AdHostAccountAdOrderHourStatus match(String key){
		AdHostAccountAdOrderHourStatus[] values = AdHostAccountAdOrderHourStatus.values();
		for(AdHostAccountAdOrderHourStatus engine:values){
			if(engine.getKey().equals(key)){
				return engine;
			}
		}
		return null;
	}
	public String getKey(){
		return this.key;
	}
	public String getLabel(){
		return this.label;
	} 

  private static class EnumCommonImpl implements EnumCommon{
		static{
			DictHandlerImpl.register(new EnumCommonImpl());
		}
			@Override
			public String getEnumPk() {
				return DICT_KEY;
			}
		
			@Override
			public String getDictName() {
				return DICT_NAME;
			}
			
			@Override
			public List<DictItem> getItems() {
				  AdHostAccountAdOrderHourStatus[] values = AdHostAccountAdOrderHourStatus.values();
				  List<DictItem> result = new ArrayList<DictItem>();
				  for(AdHostAccountAdOrderHourStatus status:values){
					  DictItem item = new DictItem(status.getKey(), status.getLabel());
					  result.add(item);
				  }
				return result;
			}
		
			@Override
			public DictItem getItem(String key) {
				List<DictItem> items = this.getItems();
				for(DictItem item:items){
					if(item.getKey().equals(key)){
						return item;
					}
				}
				return null;
			}
	}
}
