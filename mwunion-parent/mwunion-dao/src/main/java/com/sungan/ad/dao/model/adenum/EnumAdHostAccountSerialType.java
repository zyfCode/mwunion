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
 * @date 2017年3月29日
 */
public enum EnumAdHostAccountSerialType {
	PAY("0","充值 "),
	CONSUME("1","扣款"),
	REFUND("2","退款")
	;//0 充值 1 扣款 2退款
	public static final String DICT_KEY="ENUMADHOSTACCOUNTSERIAL_STATUS";
	public static final String DICT_NAME="站点状态";
	private String key;
	private String label;
	
	
	private EnumAdHostAccountSerialType(String key, String label) {
		this.key = key;
		this.label = label;
		try {
			Class.forName(EnumCommonImpl.class.getName());
		} catch (Exception e) {
			throw new AdRuntimeException("",e);
		}
	}
	public static EnumAdHostAccountSerialType match(String key){
		EnumAdHostAccountSerialType[] values = EnumAdHostAccountSerialType.values();
		for(EnumAdHostAccountSerialType engine:values){
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
				  EnumStmasterStatus[] values = EnumStmasterStatus.values();
				  List<DictItem> result = new ArrayList<DictItem>();
				  for(EnumStmasterStatus status:values){
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
