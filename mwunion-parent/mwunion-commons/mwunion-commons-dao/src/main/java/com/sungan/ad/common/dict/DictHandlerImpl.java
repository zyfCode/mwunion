package com.sungan.ad.common.dict;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sungan.ad.commons.dict.DictHandler;
import com.sungan.ad.commons.dict.DictItem;
import com.sungan.ad.exception.AdRuntimeException;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月26日
 */
public class DictHandlerImpl extends DictHandler{
	private static Log log = LogFactory.getLog(DictHandlerImpl.class); 
	private static Map<String,EnumCommon> pool = new ConcurrentHashMap<String,EnumCommon>();
	
	public static void register(EnumCommon common){
		EnumCommon enumCommon = pool.get(common.getEnumPk());
		if(enumCommon!=null){
			throw new AdRuntimeException("字段项"+common.getEnumPk()+" "+common.getDictName()+" 已经存在");
		}
		pool.put(common.getEnumPk(), common);
	}
	@Override
	public List<DictItem> getDict(String dictKind) {
		EnumCommon enumCommon = pool.get(dictKind);
		if(enumCommon==null){
			if(log.isWarnEnabled()){
				log.warn("字典项"+dictKind+"不存在");
			}
			return null;
		}
		List<DictItem> items = enumCommon.getItems();
		return items;
	}

	@Override
	public DictItem getDictItem(String dictKind, String key) {
		EnumCommon enumCommon = pool.get(dictKind);
		if(enumCommon==null){
			if(log.isWarnEnabled()){
				log.warn("字典项"+dictKind+"不存在");
			}
			return null;
		}
		DictItem item = enumCommon.getItem(key);
		return item;
	}

}
