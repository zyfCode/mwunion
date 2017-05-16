package com.sungan.ad.common.dao;

import com.sungan.ad.commons.AdCommonsUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

import javax.persistence.Id;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by zhangyf18255 on 2017/5/4.
 */
public class UpdateByCondition<T> {
    private T instance;
    private String hql;
    private Map<String,Object> param;
    private boolean lockModel = false;

    public T getEntity() {
        return instance;
    }

    protected  void init(){
        param = new LinkedHashMap<>();
        if(StringUtils.isBlank(hql)){
            hql = "update "+instance.getClass().getName();
            Map<String, Object> beanFile = null;
            try {
               beanFile = AdCommonsUtil.getBeanFile(instance);
            } catch (Exception e) {
                throw new RuntimeException("",e);
            }
            Field[] declaredFields = instance.getClass().getDeclaredFields();
            Field idFile = null;
            Object idVal = null;
            Field versionFile = null;
            Object varsionVal = null;

            for(Field f:declaredFields){
                Id annotation = f.getAnnotation(Id.class);
                Object val = beanFile.get(f.getName());
                if(val==null){
                    continue;
                }

                if(this.lockModel){
                    if(f.getName().equalsIgnoreCase("VERSION")&&val instanceof Integer){
                        versionFile = f;
                        varsionVal = val;
                        Integer version = (Integer) val;
                        val = (version+1);
                    }
                }
                if(annotation!=null){
                    idFile = f;
                    idVal = val;
                }else{
                    this.andPro(f.getName(),val);
                }
            }
            if(idFile==null||idVal==null){
                throw new RuntimeException(instance.getClass().getName()+"理新的主键不能为空!");
            }
            this.andWhere(idFile.getName(),idVal);
            if(this.lockModel){
                if(versionFile==null||varsionVal==null){
                    throw new RuntimeException(instance.getClass().getName()+"更新新的版本号不能为空!");
                }
                this.andWhere(versionFile.getName(),varsionVal);
            }
        }
    }

    public UpdateByCondition(T intance) {
        this.instance = intance;
        init();
    }
    public UpdateByCondition(T intance,boolean lockModel) {
        this.instance = intance;
        this.lockModel = lockModel;
        init();
    }


    public String getHql(){
        return  this.hql;
    }

    public Map<String,Object> getParam(){
        return  param;
    }


    public UpdateByCondition andWhere(String proName,Object value){
        if(hql.contains(" where ")) {
                this.hql = hql+" and "+proName+"=:"+proName+"_wh";
        }else{
            this.hql = hql+" where "+proName+"=:"+proName+"_wh";
        }
        param.put(proName+"_wh",value);
        return this;
    }

    protected UpdateByCondition andPro(String proName,Object value){
        if(hql.contains(" set ")){
            this.hql = hql + "," + proName + "=:"+proName;
        }else {
            this.hql = hql + " set " + proName + "=:"+proName;
        }
        param.put(proName,value);
        return this;
    }

}
