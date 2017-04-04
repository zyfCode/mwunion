package com.sungan.ad.commons.dict;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import com.sungan.ad.commons.DictUtil;
import com.sungan.ad.commons.paclscan.ClasspathPackageScanner;
import com.sungan.ad.commons.paclscan.PackageScanner;

/**
 * 说明:
 * @version V1.1
 */
public abstract class DictHandler implements InitializingBean{
	/**
	 * 根据数据字典大类获取字典属性
	 * @param dictKind
	 * @return
	 */
	public abstract List<DictItem> getDict(String dictKind);
	
	/**
	 * 根据数据字典以及key值获取数据字典值
	 * @param dictKind  字典大类
	 * @param key      字典小类
	 * @return
	 */
	public abstract DictItem getDictItem(String dictKind,String key);

	@Override
	public void afterPropertiesSet() throws Exception {
		DictUtil.setDictHandler(this);
	}
	
	
}
