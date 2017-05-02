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
public enum EnumProductPubicSources {
	PC("0","PC"),
	ISO("1","苹果"),
	android("2","安卓")
	;//
	public static final String DICT_KEY="PRODUCT_SOURCES";
	public static final String DICT_NAME="投放平台";
	private String key;
	private String label;


	 EnumProductPubicSources(String key, String label) {
		this.key = key;
		this.label = label;
		try {
			Class.forName(EnumCommonImpl.class.getName());
		} catch (Exception e) {
			throw new AdRuntimeException("",e);
		}
	}

	public static EnumProductPubicSources match(String key){
		EnumProductPubicSources[] values = EnumProductPubicSources.values();
		for(EnumProductPubicSources engine:values){
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
				  EnumProductPubicSources[] values = EnumProductPubicSources.values();
				  List<DictItem> result = new ArrayList<DictItem>();
				  for(EnumProductPubicSources status:values){
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
