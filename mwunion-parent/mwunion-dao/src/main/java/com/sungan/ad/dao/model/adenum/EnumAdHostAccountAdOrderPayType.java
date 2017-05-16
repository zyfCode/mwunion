package com.sungan.ad.dao.model.adenum;

import com.sungan.ad.common.dict.DictHandlerImpl;
import com.sungan.ad.common.dict.EnumCommon;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.exception.AdRuntimeException;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明:
 *   站点结算方式
 * @author zhangyf
 * @date 2017年3月29日
 */
public enum EnumAdHostAccountAdOrderPayType {
	SETTLE_PREPAID("0","预付"),
	SETTLE_REALTIME("1","实时扣费")
	;//贴片 刷量
	public static final String DICT_KEY="ADHOSTACCOUNTPAYORDERPAY_TYPE";
	public static final String DICT_NAME="结算方式";
	private String key;
	private String label;

	private EnumAdHostAccountAdOrderPayType(String key, String label) {
		this.key = key;
		this.label = label;
		try {
			Class.forName(EnumCommonImpl.class.getName());
		} catch (Exception e) {
			throw new AdRuntimeException("",e);
		}
	}

	public static EnumAdHostAccountAdOrderPayType match(String key){
		EnumAdHostAccountAdOrderPayType[] values = EnumAdHostAccountAdOrderPayType.values();
		for(EnumAdHostAccountAdOrderPayType engine:values){
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
				EnumAdHostAccountAdOrderPayType[] values = EnumAdHostAccountAdOrderPayType.values();
				  List<DictItem> result = new ArrayList<DictItem>();
				  for(EnumAdHostAccountAdOrderPayType status:values){
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
