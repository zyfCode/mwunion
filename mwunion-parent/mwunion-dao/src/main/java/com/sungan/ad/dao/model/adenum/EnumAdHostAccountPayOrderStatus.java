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
public enum EnumAdHostAccountPayOrderStatus {
	UNSURE("0","未确认 "),
	PAYING("1","待确认 "),
	SHOWED("2","充值成功 "),
	PAY_FAIL("3","充值失败")
	;//0 未确认 1 待确认 2 已确认 3充值失败
	public static final String DICT_KEY="ADHOSTACCOUNTPAYORDER_STATUS";
	public static final String DICT_NAME="站点状态";
	private String key;
	private String label;

	private EnumAdHostAccountPayOrderStatus(String key, String label) {
		this.key = key;
		this.label = label;
		try {
			Class.forName(EnumCommonImpl.class.getName());
		} catch (Exception e) {
			throw new AdRuntimeException("",e);
		}
	}
	public static EnumAdHostAccountPayOrderStatus match(String key){
		EnumAdHostAccountPayOrderStatus[] values = EnumAdHostAccountPayOrderStatus.values();
		for(EnumAdHostAccountPayOrderStatus engine:values){
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
