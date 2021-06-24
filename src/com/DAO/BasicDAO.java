package com.DAO;

import com.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class BasicDAO<E> {

    private QueryRunner qr = new QueryRunner();

    public int dmlUpdate(String sql, Object ... params){
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();

            return qr.update(connection, sql, params);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }

    public List<E> queryMulti(String sql, Class<E> eClass, Object... params){
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return qr.query(connection, sql, new BeanListHandler<E>(eClass), params);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }

    public E querySingle(String sql, Class<E> eClass, Object... params){
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return qr.query(connection, sql, new BeanHandler<E>(eClass), params);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }

    public Object queryScalar(String sql, Object... params){
        Connection connection = null;
        try {
            connection = DruidUtils.getConnection();
            return qr.query(connection, sql, new ScalarHandler(), params);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DruidUtils.close(null, null, connection);
        }
    }
}
