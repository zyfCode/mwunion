package com.sungan.ad.commons;
/**
 * 说明:
 * 
 * @author zhangyf
 * @date 2017年3月31日
 */
public class IdGeneratorFactory {
	private static IdGenerator idGenerator = new IdGeneratorImpl();
	
	public static String nextId(){
		return idGenerator.getNextId();
	}

	
}
