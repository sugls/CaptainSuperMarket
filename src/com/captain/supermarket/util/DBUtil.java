package com.captain.supermarket.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 数据库连接访问工具类
 * @author lsc
 *         createtime 2017年 01月 29日 星期日 下午12:52
 */
public class DBUtil {
    private Connection conn;

    /**
     * 无参构造方法，初始化
     */
    public DBUtil() {
        InputStream is = super.getClass().getClassLoader().getResourceAsStream("database.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is!=null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Class.forName(properties.getProperty("driver"));
            conn = DriverManager.getConnection(properties.getProperty("url"),properties.getProperty("username"),properties.getProperty("password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 建立sql语句发送通道
     * @param sql sql语句
     * @return PreparedStatement
     */
    public PreparedStatement getPreparedStatement(String sql){
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public void close(){
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建立存储过程语句发送通道
     * @param sql sql语句
     * @return CallableStatement
     */
    public CallableStatement getCallableStatement(String sql){
        CallableStatement cs = null;
        try {
            cs = conn.prepareCall(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cs;
    }

    /**
     * 重载 getPreparedStatement 方法
     * @param sql1 sql语句
     * @param returnGeneratedKeys 返回自增主键id的值
     * @return PreparedStatement
     */
    public PreparedStatement getPreparedStatement(String sql1, int returnGeneratedKeys) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public Connection getConn() {
        return conn;
    }
}
