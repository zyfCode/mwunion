package com.sungan.ad.common.dict;

import java.util.List;

import com.sungan.ad.commons.dict.DictItem;

/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月26日
 */
public interface EnumCommon {
	/**
	 * 获取字典项大类
	 * @return
	 */
	String getEnumPk();
	/**
	 * 字典名称 
	 * @return
	 */
	String getDictName();
	/**
	 * 获取所有的字典项s
	 * @return
	 */
	List<DictItem> getItems();
	/**
	 * 获取字典值
	 * @param key
	 * @return
	 */
	DictItem getItem(String key);
	
}
