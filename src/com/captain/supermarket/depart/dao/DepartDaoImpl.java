package com.captain.supermarket.depart.dao;

import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.util.CloseStream;
import com.captain.supermarket.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 下午12:35
 */
public enum  DepartDaoImpl implements IDepartDao {
    INSTANCE;
    DepartDaoImpl(){
    }
    @Override
    public List<Department> getDepart() {
        List<Department> list = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = "select departid,departname,description from department";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()){
                Department depart = new Department();
                depart.setDepartid(rs.getInt("departid"));
                depart.setDepartname(rs.getString("departname"));
                depart.setDescription(rs.getString("description"));
                list.add(depart);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs,ps);
        }
        dbUtil.close();
        return list;
    }

    @Override
    public String addDepart(Department depart) {
        String result = null;
        DBUtil dbUtil = new DBUtil();
        String sql = "insert into department(departname,description) values(?,?)";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setString(1,depart.getDepartname());
            ps.setString(2,depart.getDescription());
            if (ps.executeUpdate()!=0);
                result = "successful";
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode()==1062){
                result = "this department already exists";
            }
        }
        return result;
    }

    @Override
    public Department getDepartById(int departid) {
        Department depart = new Department();
        DBUtil dbUtil = new DBUtil();
        String sql = "select departname,description from department where departid = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1,departid);
            rs = ps.executeQuery();
            if (rs.next()){
                depart.setDepartid(departid);
                depart.setDepartname(rs.getString("departname"));
                depart.setDescription(rs.getString("description"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs,ps);
        }
        dbUtil.close();
        return depart;
    }

    @Override
    public String updateDepart(Department depart) {
        String result = null;
        DBUtil dbUtil = new DBUtil();
        String sql = "update department set departname = ? , description = ? where departid = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setString(1,depart.getDepartname());
            ps.setString(2,depart.getDescription());
            ps.setInt(3,depart.getDepartid());
            if (ps.executeUpdate()!=0){
                result = "successful";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getErrorCode()==1062){
                result = "this department is already exist";
            }
        } finally {
            CloseStream.close(ps);
        }
        dbUtil.close();
        return result;
    }

}
