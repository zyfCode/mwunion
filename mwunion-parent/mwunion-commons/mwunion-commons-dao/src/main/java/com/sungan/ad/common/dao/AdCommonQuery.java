package com.sungan.ad.common.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 * 说明:
 * @version V1.1
 */
public abstract class AdCommonQuery<T> {
	private Log log = LogFactory.getLog(AdCommonQuery.class);
	@Autowired
	@Qualifier("hibernateTemprate")
	protected HibernateTemplate template;

	/**
	 * 获取别名
	 * @param clazz
	 * @return
	 */
	protected String getAlia(Class<?> clazz){
		String className = clazz.getName();
		if(className.contains(".")){
			String[] split = className.split("\\.");
			className = split[split.length-1].toLowerCase();
		}
		return className;
	}

	
	/**
	 * 按条件查询
	 * @param clazz
	 * @param contidion
	 * @param hqlSuffix
	 * @return
	 */
	public AdPager<T> queryPager(Class<T> clazz,T contidion,int pageIndex,int rows,String hqlSuffix,HQLHandler hqlHandler){
		Session currentSession = template.getSessionFactory().getCurrentSession();
		AdResultTransformer<T> former = new AdResultTransformer<T>(clazz,hqlSuffix,contidion);
		HQLCondition eqCondition = former.toEqOrLikeCondition();
		String hql = eqCondition.getHql();
		if(hqlHandler!=null){
			hql = hqlHandler.handleHql(hql);
		}
		if(log.isDebugEnabled()){
			log.debug("HQL QUERY:"+hql);
		}
		Query createQuery = currentSession.createQuery(hql);//setResultTransformer(former)list();
		Map<Integer, Object> paramts = eqCondition.getParamts();
		Set<Entry<Integer, Object>> entrySet = paramts.entrySet();
		for(Map.Entry<Integer, Object> entry:entrySet){
			createQuery = createQuery.setParameter(entry.getKey(), entry.getValue());
		}
		int firstResult = (pageIndex-1)*rows;
		List<T> list = createQuery.setResultTransformer(former).setFirstResult(firstResult).setMaxResults(rows).list();
		String countHql = eqCondition.getCountHql();
		if(log.isDebugEnabled()){
			log.debug("HQL COUNT:"+countHql);
		}
		Query countQuery = currentSession.createQuery(countHql);
		for(Map.Entry<Integer, Object> entry:entrySet){
			countQuery = countQuery.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) countQuery.uniqueResult();
		int countInt = count==null?0:Integer.valueOf(count.toString());
		AdPager<T> pager = new AdPager<T>(pageIndex, rows, countInt);
		pager.setRows(list);
		return pager;
	}
	/**
	 * 按条件查询
	 * @param clazz
	 * @param contidion
	 * @param hqlSuffix
	 * @return
	 */
	public List<T> queryList(Class<T> clazz,T contidion,String hqlSuffix,HQLHandler hqlHandler){
		Session currentSession = template.getSessionFactory().getCurrentSession();
		AdResultTransformer<T> former = new AdResultTransformer<T>(clazz,hqlSuffix,contidion);
		HQLCondition eqCondition = former.toEqCondition();
		String hql = eqCondition.getHql();
		if(hqlHandler!=null){
			hql = hqlHandler.handleHql(hql);
		}
		if(log.isDebugEnabled()){
			log.debug("HQL:"+hql);
		}
		 Query createQuery = currentSession.createQuery(hql);//setResultTransformer(former)list();
		 Map<Integer, Object> paramts = eqCondition.getParamts();
		 Set<Entry<Integer, Object>> entrySet = paramts.entrySet();
		 for(Map.Entry<Integer, Object> entry:entrySet){
			 createQuery = createQuery.setParameter(entry.getKey(), entry.getValue());
		 }
		 List<T> list = createQuery.setResultTransformer(former).list();
		 return list;
	}


	protected  interface HQLHandler{
		/**
		 * 重置HQL
		 * @param orgHql
		 * @return
		 */
		String handleHql(String orgHql);
	}
}
