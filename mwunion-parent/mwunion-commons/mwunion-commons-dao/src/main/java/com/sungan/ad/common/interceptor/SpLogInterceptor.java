package com.sungan.ad.common.interceptor;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.sungan.ad.commons.DateUtil;
import com.sungan.ad.exception.AdRuntimeException;
import org.apache.commons.lang.StringUtils;
import org.hibernate.EmptyInterceptor;

import javax.persistence.Table;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhangyf18255 on 2017/5/2.
 */
public class SpLogInterceptor extends EmptyInterceptor {
    public static final ThreadLocal<InterceptorBean> local = new ThreadLocal<InterceptorBean>();
    static LoadingCache<String, String> cache = CacheBuilder.newBuilder().refreshAfterWrite(3, TimeUnit.HOURS)// 给定时间内没有被读/写访问，则回收。
            .expireAfterAccess(12, TimeUnit.HOURS)// 缓存过期时间和redis缓存时长一样
            .maximumSize(1000).// 设置缓存个数
            build(new CacheLoader<String, String>() {
        @Override
        /** 当本地缓存命没有中时，调用load方法获取结果并将结果缓存 **/
        public String load(String tableName) {
            boolean exist = CreateTableHandlerUtil.isExist(tableName);
            if (!exist) {
                return "";
            }
            return String.valueOf(exist);
        }
    });
    private static final String orgTable = "t_dayuvlog";

    private String getTableName(String id, Integer dayInt) {
        if (dayInt == null) {
            dayInt = DateUtil.dateToIntDay(new Date());
        }
        String tablename = orgTable + "_" + id + "_" + dayInt;
        return tablename;
    }

    @Override
    public String onPrepareStatement(String sql) {
        if (local.get() != null && sql.contains(orgTable)) {
            InterceptorBean bean = local.get();
            String tableName = this.getTableName(bean.getId(), bean.getDayint());
            if (!sql.contains(tableName)) { //防止递归情况出现
                String unchecked = cache.getUnchecked(tableName);
                if (StringUtils.isBlank(unchecked)) { //如果表不存在
                    cache.invalidate(tableName);
                    CreateTableHandlerUtil.createTable(orgTable, tableName);
                }
                sql = sql.replaceAll(orgTable, tableName);
            }
        }
        return sql;
    }
}
