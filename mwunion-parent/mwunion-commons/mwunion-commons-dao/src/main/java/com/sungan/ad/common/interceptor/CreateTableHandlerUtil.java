package com.sungan.ad.common.interceptor;

import java.sql.Connection;

/**
 * Created by zhangyf18255 on 2017/5/2.
 */
public class CreateTableHandlerUtil {
    private static  CreateTableHandler createTableHandler;

    public static void createTable(String orgTableName,String newTableName){
        createTableHandler.createTable(orgTableName,newTableName);
    }

    public static Connection getConnection(){
        Connection connection = createTableHandler.getConnection();
        return connection;
    }

    public static boolean isExist(String tableName){
        boolean existTable = createTableHandler.isExistTable(tableName);
        return existTable;
    }

    public static void setCreateTableHandler(CreateTableHandler createTableHandler) {
        CreateTableHandlerUtil.createTableHandler = createTableHandler;
    }
}
