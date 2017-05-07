package com.sungan.ad.dao.impl;

import com.sungan.ad.common.dao.AdPager;
import com.sungan.ad.common.dao.OrderType;
import com.sungan.ad.common.dao.QueryHandler;
import com.sungan.ad.common.interceptor.InterceptorBean;
import com.sungan.ad.common.interceptor.SpLogInterceptor;
import com.sungan.ad.dao.DayuvLogDAOAbstract;
import com.sungan.ad.dao.log.DayuvLog;
import org.hibernate.Criteria;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/2.
 */
public class DayuvLogDAOImpl extends DayuvLogDAOAbstract implements InitializingBean {
    @Autowired
    @Qualifier("hibernateTemprateLog")
    protected HibernateTemplate template;

    protected void preHandle(String adhostId){
        if(adhostId==null){
            throw new RuntimeException("分表站长ID不允许为空!");
        }
        InterceptorBean value = new InterceptorBean();
        value.setId(adhostId);
        SpLogInterceptor.local.set(value);
    }
    protected void afterHandle(){
        SpLogInterceptor.local.remove();
    }

    @Override
    public AdPager<DayuvLog> queryPageInOrder(DayuvLog dayuvLog, QueryHandler handler, int pageIndex, int rows, OrderType orderType, String orderColumn) {

        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.queryPageInOrder(dayuvLog, handler, pageIndex, rows, orderType, orderColumn);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public Collection<DayuvLog> queryIsEmpty(QueryHandler handler, String... proNames) {
        return null;
    }

    @Override
    public AdPager<DayuvLog> queryPage(DayuvLog dayuvLog, QueryHandler handler, int pageIndex, int rows) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.queryPage(dayuvLog, handler, pageIndex, rows);
        }  finally {
            this.afterHandle();
        }
    }

    @Override
    public AdPager<DayuvLog> queryPageEq(DayuvLog dayuvLog, QueryHandler handler, int pageIndex, int rows) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.queryPageEq(dayuvLog, handler, pageIndex, rows);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public Long count(DayuvLog dayuvLog, QueryHandler handler) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.count(dayuvLog, handler);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public int insert(Collection<DayuvLog> collection) {
        if(collection==null||collection.isEmpty()){
            return 0;
        }
        try {
            List<DayuvLog> dataList = (List<DayuvLog>) collection;
            String preAdHostId = null;
            for(DayuvLog log:dataList){
                if(preAdHostId==null){
                    preAdHostId = log.getAdHostId();
                }else{
                    if(!preAdHostId.equals(log.getAdHostId())){
                        throw new RuntimeException("集合adhostId必须一样");
                    }
                }
            }
            DayuvLog dayuvLog = dataList.get(0);
            this.preHandle(dayuvLog.getAdHostId());
            int insert = super.insert(collection);
            return insert;
        } finally {

        }
    }

    @Override
    public Collection<DayuvLog> query() {
        return null;
    }

    @Override
    public AdPager<DayuvLog> queryPageEq(DayuvLog dayuvLog, int pageIndex, int rows) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.queryPageEq(dayuvLog, pageIndex, rows);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public AdPager<DayuvLog> queryPage(DayuvLog dayuvLog, int pageIndex, int rows) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.queryPage(dayuvLog, pageIndex, rows);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public AdPager<DayuvLog> queryPageInOrder(DayuvLog dayuvLog, int pageIndex, int rows, OrderType orderType, String orderColumn) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.queryPageInOrder(dayuvLog, pageIndex, rows, orderType, orderColumn);
        } finally {
            this.afterHandle();
        }
    }


    @Override
    public Collection<DayuvLog> query(DayuvLog dayuvLog, QueryHandler handler) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.query(dayuvLog, handler);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public Collection<DayuvLog> queryIsEmpty(String... proNames) {
        return null;
    }

    @Override
    public Collection<DayuvLog> query(DayuvLog dayuvLog) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.query(dayuvLog);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public Long count(DayuvLog dayuvLog) {
        try {
            this.preHandle(dayuvLog.getAdHostId());
            return super.count(dayuvLog);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public Serializable insert(DayuvLog dayuvLog) {
        try {
            this.preHandle(dayuvLog.getId());
            return super.insert(dayuvLog);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public void delete(DayuvLog dayuvLog) {
        try {
            this.preHandle(dayuvLog.getId());
            super.delete(dayuvLog);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public void delete(Collection<DayuvLog> collection) {

    }

    @Override
    public Collection<DayuvLog> query(int beginIndex, int num, String orderclum, OrderType orderType) {
        return null;
    }

    @Override
    public void update(DayuvLog dayuvLog) {
        try {
            this.preHandle(dayuvLog.getId());
            super.update(dayuvLog);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public DayuvLog find(Serializable id) {
        try {
            this.preHandle(id.toString());
            return super.find(id);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public DayuvLog findByLoad(Serializable id) {
        try {
            this.preHandle(id.toString());
            return super.findByLoad(id);
        } finally {
            this.afterHandle();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        super.template = this.template;
    }
}
