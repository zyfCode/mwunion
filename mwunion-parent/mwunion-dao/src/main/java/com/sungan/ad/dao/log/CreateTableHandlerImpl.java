package com.sungan.ad.dao.log;

import com.sungan.ad.common.interceptor.CreateTableHandler;
import com.sungan.ad.common.interceptor.CreateTableHandlerUtil;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangyf18255 on 2017/5/2.
 */
@Component
public class CreateTableHandlerImpl implements CreateTableHandler,InitializingBean {
    @Autowired
    @Qualifier("hibernateTemprateLog")
    protected HibernateTemplate template;

    private static final Logger logger = LoggerFactory.getLogger(CreateTableHandlerImpl.class);

    @Override
    public void createTable(String orgTableName, String newTableName) {
        boolean existTable = this.isExistTable(newTableName);
        if(existTable){
            return;
        }
        try {
            PreparedStatement preparedStatement = null;
            try {
                Connection connection = this.getConnection();
                String sql = "CREATE  TABLE  " + newTableName + "  LIKE  "+orgTableName+";";
                 preparedStatement = connection.prepareStatement(sql);
                boolean execute = preparedStatement.execute();
                logger.info("创建表{}结果{}",newTableName,execute);
            } finally {
                if(preparedStatement!=null){
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
            logger.error("",e);
        }
    }

    public boolean isExistTable(String tableName){
        try {
            String sql = "SHOW TABLES LIKE '" + tableName + "'";
            Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            try {
                boolean execute = preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();
                try {
                    if(resultSet.next()){
                        Object object = resultSet.getObject(1);
                        logger.debug("查询表{}结果{}",tableName,object);
                        if(object!=null&&StringUtils.isNotBlank(object.toString())){
                            return true;
                        }
                    }
                } finally {
                    if(resultSet!=null) {
                        resultSet.close();
                    }
                }
            } finally {
                if(preparedStatement!=null) {
                    preparedStatement.close();
                }
            }
        } catch (SQLException e) {
           logger.error("",e);
        }
        return false;
    }

    @Override
    public Connection getConnection() {
        try {
            Connection connection = SessionFactoryUtils.getDataSource(template.getSessionFactory()).getConnection();
            return connection;
        } catch (SQLException e) {
            logger.error("",e);
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        CreateTableHandlerUtil.setCreateTableHandler(this);
    }
}
