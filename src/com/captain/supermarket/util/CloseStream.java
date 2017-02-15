package com.captain.supermarket.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 资源释放工具类
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 下午9:21
 */
public class CloseStream {
    /**
     * 释放资源
     * @param resultSet ResultSet 对象
     * @param preparedStatement PreparedStatement 对象
     */
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement){
        close(preparedStatement);
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 重载方法
     * @param preparedStatement PreparedStatement 对象
     */
    public static void close(PreparedStatement preparedStatement){
        try {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 重载方法
     * @param resultSet ResultSet 对象
     * @param preparedStatement PreparedStatement 对象
     * @param callableStatement CallableStatement 对象
     */
    public static void close(ResultSet resultSet,PreparedStatement preparedStatement,CallableStatement callableStatement){
        close(resultSet,preparedStatement);
        if (callableStatement!=null){
            try {
                callableStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
