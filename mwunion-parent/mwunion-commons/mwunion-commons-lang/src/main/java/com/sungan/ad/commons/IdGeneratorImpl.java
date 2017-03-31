package com.sungan.ad.commons;

import java.util.UUID;

/**
 * 说明:
 * @author zhangyf
 * @date 2017年3月31日
 */
public class IdGeneratorImpl implements IdGenerator{
	@Override
	public String getNextId() {
		String replaceAll = UUID.randomUUID().toString().replaceAll("-", "");
		return replaceAll;
	}
}
