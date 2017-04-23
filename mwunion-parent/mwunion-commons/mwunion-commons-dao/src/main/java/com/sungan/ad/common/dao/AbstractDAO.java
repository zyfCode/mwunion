package com.sungan.ad.common.dao;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.sungan.ad.commons.AdCommonsUtil;

public abstract class AbstractDAO<T> implements DAO<T> {
	private static final Log log = LogFactory.getLog(AbstractDAO.class);
	@Autowired
	protected HibernateTemplate template;
	@SuppressWarnings("rawtypes")
	protected Class currentClass;
	
	public AbstractDAO(){}

	{
		Type type = this.getClass().getSuperclass().getGenericSuperclass();
		if (type != null && type instanceof ParameterizedType) {
			ParameterizedType c = (ParameterizedType) type;
			Type[] typeArr = c.getActualTypeArguments();
			if (typeArr != null && typeArr.length == 1) {
				Type actualType = typeArr[0];
				if (actualType instanceof Class) {
					this.currentClass = (Class) actualType;
				} else {
					log.error("非class类型：" + actualType);
				}
			}
		}
	}

	@Override
	public AdPager<T> queryPageInOrder(T t, QueryHandler handler, int pageIndex, int rows, OrderType orderType, String orderColumn) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		this.setCondition(t,createCriteria);
		if(handler!=null){
			createCriteria = handler.addCondition(createCriteria);
		}
		int firstResult = (pageIndex-1)*rows;
		createCriteria.setFirstResult(firstResult).setMaxResults(rows);
		List<T> list = null;
		if(orderColumn!=null&&orderType!=null){
			if(orderType==OrderType.DESC){
				list=createCriteria.addOrder(Order.desc(orderColumn)).list();
			}else{
				list=createCriteria.addOrder(Order.asc(orderColumn)).list();
			}
		}else{
			list=createCriteria.list();
		}
		int count = Integer.valueOf(this.count(t).toString());
		AdPager<T> pager = new AdPager<T>(pageIndex, rows, count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public Collection<T> queryIsEmpty(QueryHandler handler, String... proNames) {
		if(proNames==null||proNames.length<1){
			throw new RuntimeException("属性字段不能为空");
		}
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				for(String proName:proNames){
					Class<?> propertyType = pt.getPropertyType();
					if(proName.equals(name)){
						if(propertyType==String.class){
							createCriteria = createCriteria.add(Restrictions.or(Restrictions.isNull(name),Restrictions.eq(name, "")));
						}else{
							createCriteria = createCriteria.add(Restrictions.isNull(name));
						}
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		if(handler!=null){
			createCriteria = handler.addCondition(createCriteria);
		}
		List<T> list = createCriteria.list();
		return list;
	}

	@Override
	public AdPager<T> queryPage(T t, QueryHandler handler, int pageIndex, int rows) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null&&(object instanceof String)){
					createCriteria = createCriteria.add(Restrictions.like(name, object+"%"));
				}else if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
		if(handler!=null){
			createCriteria = handler.addCondition(createCriteria);
		}
		int firstResult = (pageIndex-1)*rows;
		createCriteria.setFirstResult(firstResult).setMaxResults(rows);
		List<T> list = createCriteria.list();
		int count = Integer.valueOf(this.count(t).toString());
		AdPager<T> pager = new AdPager<T>(pageIndex, rows, count);
		pager.setRows(list);
		return pager;
	}

	@Override
	public AdPager<T> queryPageEq(T t, QueryHandler handler, int pageIndex, int rows) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		setCondition(t,createCriteria);
		if(handler!=null){
			createCriteria = handler.addCondition(createCriteria);
		}
		int firstResult = (pageIndex-1)*rows;
		createCriteria.setFirstResult(firstResult).setMaxResults(rows);
		List<T> list = createCriteria.list();
		int count = Integer.valueOf(this.count(t).toString());
		AdPager<T> pager = new AdPager<T>(pageIndex, rows, count);
		pager.setRows(list);
		return pager;
	}

	protected AbstractDAO(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public Long count(T t, QueryHandler handler) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		this.setCondition(t,createCriteria);
		if(handler!=null){
			createCriteria  = handler.addCondition(createCriteria);
		}
		Long uniqueResult = (Long) createCriteria.setProjection(Projections.rowCount()).uniqueResult();
		return uniqueResult;
	}

	public void commit() {
		Session currentSession = template.getSessionFactory()
				.getCurrentSession();
		currentSession.getTransaction().commit();
	}

	public int insert(Collection<T> collection) {
		if (collection == null) {
			throw new NullPointerException();
		}
		int count = 0;
		Session currentSession = template.getSessionFactory()
				.getCurrentSession();
		try {
			for (T t : collection) {
				currentSession.save(t);
				count++;
				if (++count % 40 == 0) {
					currentSession.flush();
					currentSession.clear();
				}
			}
		} finally {
			currentSession.flush();
			currentSession.clear();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> query() {
		StringBuffer buf = new StringBuffer();
		buf.append("from ").append(AbstractDAO.this.currentClass.getName());
		Session 
			currentSession = template.getSessionFactory().getCurrentSession();
		List<T> find  = currentSession.createQuery(buf.toString()).list();
		return find;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public AdPager<T> queryPageEq(T t,int pageIndex,int rows) {
		return this.queryPageEq(t,null,pageIndex,rows);
	}
	
	@SuppressWarnings("unchecked")
	public AdPager<T> queryPage(T t,int pageIndex,int rows) {
		AdPager<T> tAdPager = this.queryPage(t, null, pageIndex, rows);
		return tAdPager;
	}
	@SuppressWarnings("unchecked")
	public AdPager<T> queryPageInOrder(T t,int pageIndex,int rows,OrderType orderType,String orderColumn) {
		AdPager<T> tAdPager = this.queryPageInOrder(t, null, pageIndex, rows, orderType, orderColumn);
		return tAdPager;
	}

	private void setCondition(T t,Criteria createCriteria){
		try {
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(t);
			BeanInfo beanInfo = Introspector.getBeanInfo(currentClass);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor pt:propertyDescriptors){
				String name = pt.getName();
				if(name.equals("class")){
					continue;
				}
				Object object = beanFile.get(name);
				if(object!=null){
					createCriteria = createCriteria.add(Restrictions.eq(name, object));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("",e);
		}
	}
	@SuppressWarnings("unchecked")
	public Collection<T> query(T t,QueryHandler handler) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		setCondition(t,createCriteria);
		List<T> list = createCriteria.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public Collection<T> queryIsEmpty(String... proNames) {
		Collection<T> ts = this.queryIsEmpty(null, proNames);
		return ts;
	}
	@SuppressWarnings("unchecked")
	public Collection<T> query(T t) {
		Session currentSession = this.template.getSessionFactory().getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(currentClass);//.add(Restrictions.eq("appid", appid)).list();
		this.setCondition(t,createCriteria);
		List<T> list = createCriteria.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public Long count(T t) {
		Long count = this.count(t, null);
		return count;
	}

	@SuppressWarnings("unchecked")
	public long count() {
		String hql = "select count(*) from " + this.currentClass.getName();
		List<Long> count = template.find(hql);
		if (count != null && count.size() > 0) {
			return count.get(0);
		}
		return 0;
	}

	public Serializable insert(T t) {
		Session  currentSession = template.getSessionFactory().getCurrentSession();
		Serializable  save = currentSession.save(t);
		return save;
	}

	public void delete(T t) {
		Session currentSession = template.getSessionFactory().getCurrentSession();
		 currentSession.delete(t);
	}

	public void delete(Collection<T> collection) {
		Session currentSession = template.getSessionFactory().getCurrentSession();
		for (T t : collection) {
			currentSession.delete(t);
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<T> query(final int beginIndex, final int num,
			String orderclum, OrderType orderType) {
		Session  currentSession = template.getSessionFactory().getCurrentSession();
		Criteria setMaxResults = currentSession.createCriteria(AbstractDAO.this.currentClass).setFirstResult(beginIndex).setMaxResults(num);
		if(StringUtils.isNotBlank(orderclum)){
			if(orderType!=null){
				if(orderType==OrderType.DESC){
					setMaxResults.addOrder(Order.desc(orderclum));
				}else{
					setMaxResults.addOrder(Order.asc(orderclum));
				}
			}else{
				setMaxResults.addOrder(Order.asc(orderclum));
			}
		}
		List<T> find = setMaxResults.list();
		return find;
	}

	public void update(T t) {
		Session  currentSession = template.getSessionFactory().getCurrentSession();
		 currentSession.update(t);
	}

	@SuppressWarnings("unchecked")
	public T find(Serializable id) {
		Session currentSession  = template.getSessionFactory().getCurrentSession();
		T  t = (T) currentSession.get(currentClass, id);
		return t;
	}

//	@Override
	@SuppressWarnings("unchecked")
	public T findByLoad(Serializable id) {
		Session currentSession  = template.getSessionFactory().getCurrentSession();
		T  t = (T) currentSession.load(currentClass, id);
		return t;
	}

	
}
