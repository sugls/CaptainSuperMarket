package com.captain.supermarket.util;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 下午9:21
 */
public class CloseStream {
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement){
        try{
            if (resultSet!=null){
                resultSet.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(PreparedStatement preparedStatement){
        try {
            if (preparedStatement!=null){
                preparedStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void close(ResultSet resultSet,PreparedStatement preparedStatement,CallableStatement callableStatement){
        try {
            if (resultSet!=null){
                resultSet.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (callableStatement!=null){
                callableStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
