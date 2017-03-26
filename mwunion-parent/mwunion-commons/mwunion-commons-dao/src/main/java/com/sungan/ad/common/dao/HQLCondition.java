package com.sungan.ad.common.dao;

import java.util.Map;

/**
 * 说明:
 */
public class HQLCondition {
	private String hql;
	private String countHql;
	private Map<Integer,Object> paramts;
	
	
	@Override
	public String toString() {
		return "HQLCondition [\r\nhql=" + hql + " \r\ncountHql=" + countHql + " \r\nparamts=" + paramts + "\r\n]";
	}


	public HQLCondition(String hql,String countHql,  Map<Integer, Object> paramts) {
		super();
		this.hql = hql;
		this.paramts = paramts;
		this.countHql = countHql;
	}
	
	
	public String getCountHql() {
		return countHql;
	}


	public void setCountHql(String countHql) {
		this.countHql = countHql;
	}


	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public Map<Integer, Object> getParamts() {
		return paramts;
	}
	public void setParamts(Map<Integer, Object> paramts) {
		this.paramts = paramts;
	}
	
}
