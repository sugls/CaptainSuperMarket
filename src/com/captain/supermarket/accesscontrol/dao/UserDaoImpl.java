package com.captain.supermarket.accesscontrol.dao;

import com.captain.supermarket.accesscontrol.vo.Users;
import com.captain.supermarket.util.CloseStream;
import com.captain.supermarket.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户相关业务实现类
 * @author lsc
 *         createtime 2017年 02月 02日 星期四 下午9:01
 */
public class UserDaoImpl implements IUserDao{

    @Override
    public Users checkLogin(String username, String password) {
        Users user = null;
        DBUtil dbUtil = new DBUtil();
        String sql = "SELECT useridentity FROM user WHERE password = ? AND username = ?";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setString(1,password);
            ps.setString(2,username);
            rs = ps.executeQuery();
            if (rs.next()){
                user = new Users();
                user.setUsername(username);
                user.setPassword(password);
                user.setUseridentity(rs.getInt("useridentity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs,ps);
        }
        dbUtil.close();
        return user;
    }
}
