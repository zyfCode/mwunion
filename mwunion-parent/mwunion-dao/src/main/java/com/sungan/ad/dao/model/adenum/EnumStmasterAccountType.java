package com.sungan.ad.dao.model.adenum;

import java.util.ArrayList;
import java.util.List;

import com.sungan.ad.common.dict.DictHandlerImpl;
import com.sungan.ad.common.dict.EnumCommon;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.exception.AdRuntimeException;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月26日
 */
public enum EnumStmasterAccountType{
	NORMAL("0","支付宝 "),DISABLE("1","微信 "),CANCEL("2","银联 ");//* 0正常   1停用   2注销
	public static final String DICT_KEY="STMASTER_ACCOUNTTYPE";
	public static final String DICT_NAME="站长账号";
	private String key;
	private String label;
	
	
	private EnumStmasterAccountType(String key, String label) {
		this.key = key;
		this.label = label;
		try {
			Class.forName(EnumCommonImpl.class.getName());
		} catch (Exception e) {
			throw new AdRuntimeException("",e);
		}
	}

	public static EnumStmasterAccountType match(String key){
		EnumStmasterAccountType[] values = EnumStmasterAccountType.values();
		for(EnumStmasterAccountType engine:values){
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
				  EnumStmasterAccountType[] values = EnumStmasterAccountType.values();
				  List<DictItem> result = new ArrayList<DictItem>();
				  for(EnumStmasterAccountType status:values){
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
