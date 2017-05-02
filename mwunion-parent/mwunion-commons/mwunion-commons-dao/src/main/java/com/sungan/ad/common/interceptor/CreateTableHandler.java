package com.sungan.ad.common.interceptor;

import java.sql.Connection;

/**
 * Created by zhangyf18255 on 2017/5/2.
 */
public interface CreateTableHandler {
     void createTable(String orgTableName,String newTableName);
     Connection getConnection();
     boolean isExistTable(String tableName);
}
