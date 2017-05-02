package com.sungan.ad.dao.log;

import com.sungan.ad.common.interceptor.CreateTableHandler;
import com.sungan.ad.common.interceptor.CreateTableHandlerUtil;
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
        try {
            PreparedStatement preparedStatement = null;
            try {
                Connection connection = this.getConnection();
                String sql = "CREATE  TABLE  " + newTableName + "  LIKE  "+orgTableName+";";
                 preparedStatement = connection.prepareStatement(sql);
                 preparedStatement.execute();
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
        SQLQuery sqlQuery = this.template.getSessionFactory().getCurrentSession().createSQLQuery("SHOW TABLES LIKE '" + tableName + "'");
        List list = sqlQuery.list();
        if(logger.isInfoEnabled()){
            logger.info(list+"");
        }
        if(list!=null&&list.size()>0){
                return true;
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
