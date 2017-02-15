package com.captain.supermarket.manage.dao;

import com.captain.supermarket.manage.vo.Report;
import com.captain.supermarket.util.CloseStream;
import com.captain.supermarket.util.DBUtil;

import java.sql.*;
import java.util.List;
import java.util.Vector;

/**
 * 系统业务实现类
 * @author lsc
 *         createtime 2017年 02月 06日 星期一 下午12:55
 */
public class ManageDaoImpl implements IManageDao{
    @Override
    public List<Report> getReportsByDateRange(Date fromdate, Date todate,String departname) {
        List<Report> list = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = "{call report(?,?,?)}";
        CallableStatement cs = dbUtil.getCallableStatement(sql);
        String sql1 = "select departname,`date`,income,expense from t_report";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql1);
        ResultSet rs = null;
        try {
            cs.setDate(1,fromdate);
            cs.setDate(2,todate);
            cs.setString(3,departname);
            cs.executeUpdate();
            rs = ps.executeQuery();
            while (rs.next()){
                Report report = new Report();
                report.setDepartname(rs.getString("departname"));
                report.setDate(rs.getDate("date"));
                report.setIncome(rs.getDouble("income"));
                report.setExpense(rs.getDouble("expense"));
                list.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs,ps,cs);
        }
        dbUtil.close();
        return list;
    }

    @Override
    public List<String> selectAllTableName() {
        List<String> list = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'supermarket'";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("table_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return list;
    }
}
