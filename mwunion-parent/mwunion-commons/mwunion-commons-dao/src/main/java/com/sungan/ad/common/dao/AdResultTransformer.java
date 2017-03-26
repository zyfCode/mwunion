package com.sungan.ad.common.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;
import org.hibernate.transform.ResultTransformer;

import com.sungan.ad.commons.AdCommonsUtil;
import com.sungan.ad.exception.AdRuntimeException;
import com.sungan.ad.expand.common.annotation.SqlColumn;

/**
 * Result transformer that allows to transform a result to
 * a user specified class which will be populated via setter
 * methods or fields matching the alias names.
 * <p/>
 * <pre>
 * List resultWithAliasedBean = s.createCriteria(Enrolment.class)
 * 			.createAlias("student", "st")
 * 			.createAlias("course", "co")
 * 			.setProjection( Projections.projectionList()
 * 					.add( Projections.property("co.description"), "courseDescription" )
 * 			)
 * 			.setResultTransformer( new AliasToBeanResultTransformer(StudentDTO.class) )
 * 			.list();
 * <p/>
 *  StudentDTO dto = (StudentDTO)resultWithAliasedBean.get(0);
 * 	</pre>
 *
 * @author max
 */
public class AdResultTransformer<T> implements ResultTransformer, Serializable {

	// IMPL NOTE : due to the delayed population of setters (setters cached
	// 		for performance), we really cannot properly define equality for
	// 		this transformer

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Class<T> resultClass;
	private boolean isInitialized;
	private String[] aliases;
	private Setter[] setters;
	private String hqlSuffix;
	private T condition; 
	
	public AdResultTransformer(Class<T> resultClass,String hqlSuffix,T condition) {
		if ( resultClass == null ) {
			throw new IllegalArgumentException( "resultClass cannot be null" );
		}
		isInitialized = false;
		this.hqlSuffix = hqlSuffix;
		this.resultClass = resultClass;
		this.condition = condition;
	}

	private void check(){
		for(Setter setter:setters){
			if(setter==null){ 
				throw new AdRuntimeException(resultClass.getName()+" 存在无set方法属性");
			}
		}
	}
	
	
	public static String getAliName(Class<?> clazz){
		String className = clazz.getName();
		if(className.contains(".")){
			String[] split = className.split("\\.");
			className = split[split.length-1].toLowerCase();
		}
		return className;
	}
	
	
	/**
	 * 等 于查询
	 * @param condition
	 * @return
	 */
	public HQLCondition toEqCondition(){
		  List<DBColumnBean> conditionList = this.getCondition();
		  String simpleSql = this.toSimpleSql();
		  String countHql = this.getCountHql();
		Map<Integer,Object> param = new LinkedHashMap<Integer, Object>();
		if(conditionList.size()>0){
			StringBuffer bufSql = new StringBuffer(); 
			if(!simpleSql.toUpperCase().contains(" WHERE ")){
				bufSql.append(" where 1=1 ");
			}
			for (int i = 0; i < conditionList.size(); i++) {
				DBColumnBean conditoin = conditionList.get(i);
				param.put(i, conditoin.getValue());
				bufSql.append(" and ").append(conditoin.getDbColName()).append("=").append("?").append(" ");
			}
			simpleSql = simpleSql+bufSql;
			countHql = countHql+bufSql;
		}
		HQLCondition hcon = new HQLCondition(simpleSql,countHql, param);
		return hcon;
	}
	
	private boolean isContainWhere(){
		String simpleSql = this.toSimpleSql();
		if(simpleSql.toUpperCase().contains(" WHERE ")){
			return true;
		}
		return false;
	}
	
	public HQLCondition isEmpyty(String... columnName){
		List<AdResultTransformer<T>.DBColumnBean> classColumns = this.getClassColumn();
		StringBuffer buf = new StringBuffer();
		if(!this.isContainWhere()){
			buf.append(" where 1=1 ");
		}
		
		for(String cName :columnName){
			for(DBColumnBean column:classColumns){
				if(column.getFileName().equals(cName)){
					Class<?> type = column.getType();
					if(type==String.class){
						buf.append(" and (").append(column.getDbColName()).append(" is null or ").append(column.getDbColName()).append("='') ");
					}else{
						buf.append(" and ").append(column.getDbColName()).append(" is null");
					}
				}
				
			}
		}
	    String hql =	this.toSimpleSql()+buf.toString();
	    String countHql = this.getCountHql()+buf.toString();
		Map<Integer, Object> paramts = new LinkedHashMap<Integer, Object>();
		HQLCondition condition = new HQLCondition(hql,countHql, paramts );
		return condition;
	}
	
	private String getCountHql(){
		String countHql = "select count(*) "+this.hqlSuffix;
		return countHql;
	}
	
	/**
	 * 等 于查询
	 * @param condition
	 * @return
	 */
	public HQLCondition toEqOrLikeCondition(){
		List<DBColumnBean> conditionList = this.getCondition();
		String simpleSql = this.toSimpleSql();
		String countHql = this.getCountHql();
		Map<Integer,Object> param = new LinkedHashMap<Integer, Object>();
		if(conditionList.size()>0){
			StringBuffer bufSql = new StringBuffer(); 
			if(!simpleSql.toUpperCase().contains(" WHERE ")){
				bufSql.append(" where 1=1 ");
			}
			for (int i = 0; i < conditionList.size(); i++) {
				DBColumnBean conditoin = conditionList.get(i);
				Object value = conditoin.getValue();
				if(value instanceof String){
					param.put(i, conditoin.getValue()+"%");
					bufSql.append(" and ").append(conditoin.getDbColName()).append(" like ").append("?").append(" ");
				}else{
					param.put(i, conditoin.getValue());
					bufSql.append(" and ").append(conditoin.getDbColName()).append("=").append("?").append(" ");
				}
			
			}
			simpleSql = simpleSql+bufSql;
			countHql = countHql+bufSql;
		}
		HQLCondition hcon = new HQLCondition(simpleSql,countHql, param);
		return hcon;
	}
	
	/**
	 * 获取非空条件
	 * @return
	 */
	private List<DBColumnBean> getCondition(){
		try {
			List<DBColumnBean> conditons = new ArrayList<DBColumnBean>();
			if(this.condition==null){
				return conditons;
			}
			Map<String, Object> beanFile = AdCommonsUtil.getBeanFile(condition);
			Field[] declaredFields = this.resultClass.getDeclaredFields();
			for (Field f : declaredFields) {
				f.setAccessible(true);
				Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
				for (Annotation at : declaredAnnotations) {
					if (at instanceof SqlColumn) {
						String dbColName = f.getName(); 
						Object object = beanFile.get(dbColName);
						if(object==null){
							continue;
						}
						if(object instanceof String){
							if(object.toString().trim().equals("")){
								continue;
							}
						}
						SqlColumn sqlColumn = (SqlColumn) at;
						String colName = sqlColumn.colName();
						if(StringUtils.isNotBlank(colName)){
							dbColName = colName;
						}
						String className = sqlColumn.value().getName();
						if(className.contains(".")){
							String[] split = className.split("\\.");
							className = split[split.length-1].toLowerCase();
						}
						StringBuffer dbColumn = new StringBuffer(); 
						dbColumn.append(className).append(".").append(dbColName.trim());
						DBColumnBean conBean = new DBColumnBean(f.getName(), dbColumn.toString(), object);
						conditons.add(conBean);
					}
				}
			}
			return conditons;
			
		} catch (Exception e) {
			throw new AdRuntimeException("获取条件异常",e);
		}
		
	}
	
	protected class DBColumnBean{
		private String  fileName;
		private String dbColName;
		private Object value;
		private Class<?> type;
		
		public DBColumnBean(String fileName, String dbColName, Class<?> type) {
			super();
			this.fileName = fileName;
			this.dbColName = dbColName;
			this.type = type;
		}
		public DBColumnBean(String fileName, String dbColName, Object value) {
			super();
			this.fileName = fileName;
			this.dbColName = dbColName;
			this.value = value;
		}
		
		public Class<?> getType() {
			return type;
		}
		public void setType(Class<?> type) {
			this.type = type;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public String getDbColName() {
			return dbColName;
		}
		public void setDbColName(String dbColName) {
			this.dbColName = dbColName;
		}
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
	}
	/**
	 * 获取{@code this.resultClass}所有数库字段（被{@code SqlColumn}修饰的属性）
	 * @return
	 */
	private List<DBColumnBean> getClassColumn(){
		Field[] declaredFields = this.resultClass.getDeclaredFields();
		List<DBColumnBean> result = new ArrayList<DBColumnBean>();
		for (Field f : declaredFields) {
			f.setAccessible(true);
			Annotation[] declaredAnnotations = f.getDeclaredAnnotations();
			for (Annotation at : declaredAnnotations) {
				if (at instanceof SqlColumn) {
					String dbName = f.getName();
					Class<?> type = f.getType();
					SqlColumn sqlColumn = (SqlColumn) at;
					String colName = sqlColumn.colName();
					if(StringUtils.isNotBlank(colName)){
						dbName = colName;
					}
					String className = sqlColumn.value().getName();
					if(className.contains(".")){
						String[] split = className.split("\\.");
						className = split[split.length-1].toLowerCase();
					}
					StringBuffer dbColumn = new StringBuffer(); 
					dbColumn.append(className).append(".").append(dbName.trim());
					DBColumnBean colBean = new DBColumnBean(f.getName(), dbColumn.toString(), type);
					result.add(colBean);
				}
			}
		} 
		return result;
	}
	
	/**
	 * 将一个类的字段转成sql
	 * @param t
	 * @return
	 */
	private  String toSimpleSql(){
		StringBuffer buf = new StringBuffer();
		List<AdResultTransformer<T>.DBColumnBean> classColumn = this.getClassColumn();
		for (int i = 0; i < classColumn.size(); i++) {
			AdResultTransformer<T>.DBColumnBean dbColumnBean = classColumn.get(i);
			if (buf.length() < 1) {
				buf.append(" select ").append(dbColumnBean.getDbColName()).append(" ");
			} else {
				buf.append(", ").append(dbColumnBean.getDbColName()).append(" ");
			}
		}
		buf.append(this.hqlSuffix).append(" ");
		return buf.toString();
	}
	
	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result;

		try {
			if(!isInitialized){
				this.customInit(aliases);
				this.check();
			}
			result = resultClass.newInstance();
			for ( int i = 0; i < aliases.length; i++ ) {
				if ( setters[i] != null ) {
					setters[i].set( result, tuple[i], null );
				}
			}
		}
		catch ( InstantiationException e ) {
			throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
		}
		catch ( IllegalAccessException e ) {
			throw new HibernateException( "Could not instantiate resultclass: " + resultClass.getName() );
		}

		return result;
	}
	/**
	 *  自定义匹配器
	 * @return
	 */
	public void customInit(String[] aliases){
		PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
				new PropertyAccessor[] {
						PropertyAccessorFactory.getPropertyAccessor( resultClass, null ),
						PropertyAccessorFactory.getPropertyAccessor( "field" )
				}
		);
		Field[] declaredFields = this.resultClass.getDeclaredFields();
		List<AdResultTransformer<T>.DBColumnBean> classColumn = this.getClassColumn();
		setters = new Setter[ classColumn.size()];
		for (int i=0;i<classColumn.size();i++) {
			AdResultTransformer<T>.DBColumnBean dbColumnBean = classColumn.get(i);
			String name = dbColumnBean.getFileName();
			Setter setter = propertyAccessor.getSetter(resultClass, name);
			setters[i]=setter;
	    }
		isInitialized = true;
	}
	

	public List transformList(List collection) {
		return collection;
	}

	public boolean equals(Object o) {
		if ( this == o ) {
			return true;
		}
		if ( o == null || getClass() != o.getClass() ) {
			return false;
		}

		AdResultTransformer that = ( AdResultTransformer ) o;

		if ( ! resultClass.equals( that.resultClass ) ) {
			return false;
		}
		if ( ! Arrays.equals( aliases, that.aliases ) ) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		int result = resultClass.hashCode();
		result = 31 * result + ( aliases != null ? Arrays.hashCode( aliases ) : 0 );
		return result;
	}
}