package com.captain.supermarket.income.dao;

import com.captain.supermarket.income.vo.Income;
import com.captain.supermarket.util.DBUtil;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 上午11:03
 */
public class IncomeDaoImpl implements IIncomeDao{
    @Override
    public List<Income> getIncomeByDate(Date date) {
        List<Income> list = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = "select incomeid,departid,businessdate,lastmoddate,dailyincome,departname\n" +
                "from income natural join department where businessdate = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setDate(1,date);
            rs = ps.executeQuery();
            while (rs.next()){
                Income income = new Income();
                income.setIncomeid(rs.getInt("incomeid"));
                income.setDepartid(rs.getInt("departid"));
                income.setDepartname(rs.getString("departname"));
                income.setBusinessdate(rs.getDate("businessdate"));
                income.setLastmoddate(rs.getDate("lastmoddate"));
                income.setDailyincome(rs.getDouble("dailyincome"));
                list.add(income);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (rs!=null){
                    rs.close();
                }
                if (ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dbUtil.close();
        return list;
    }

    @Override
    public boolean addIncome(Income income) {
        int result = 0;
        DBUtil dbUtil = new DBUtil();
        String sql = "insert into income set dailyincome = ? , businessdate = current_date(),departid = (select departid from department where departname = ?)";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setDouble(1,income.getDailyincome());
            ps.setString(2,income.getDepartname());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return result !=0;
    }

    @Override
    public Income getIncomeById(int incomeid) {
        Income income = new Income();
        DBUtil dbUtil = new DBUtil();
        String sql = "select departid,departname,businessdate,lastmoddate,dailyincome from income \n" +
                "natural join department where incomeid = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1,incomeid);
            rs = ps.executeQuery();
            if (rs.next()){
                income.setIncomeid(incomeid);
                income.setDepartid(rs.getInt("departid"));
                income.setDepartname(rs.getString("departname"));
                income.setBusinessdate(rs.getDate("businessdate"));
                income.setLastmoddate(rs.getDate("lastmoddate"));
                income.setDailyincome(rs.getDouble("dailyincome"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (rs!=null){
                    rs.close();
                }
                if (ps!=null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        dbUtil.close();
        return income;
    }

    @Override
    public boolean updateIncome(Income income) {
        int result = 0;
        DBUtil dbUtil = new DBUtil();
        String sql = "update income set dailyincome = ?, lastmoddate = current_date() where departid = ? and businessdate = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setDouble(1,income.getDailyincome());
            ps.setInt(2,income.getDepartid());
            ps.setDate(3,income.getBusinessdate());
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return result != 0;
    }
}
