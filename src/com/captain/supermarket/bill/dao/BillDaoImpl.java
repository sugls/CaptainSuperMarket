package com.captain.supermarket.bill.dao;

import com.captain.supermarket.bill.vo.Bill;
import com.captain.supermarket.bill.vo.Billdetails;
import com.captain.supermarket.bill.vo.Payment;
import com.captain.supermarket.util.CloseStream;
import com.captain.supermarket.util.DBUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

/**
 * @author lsc
 *         createtime 2017年 02月 09日 星期四 下午5:25
 */
public class  BillDaoImpl implements IBillDao{

    @Override
    public int addBill(Bill bill) {
        int billid = -1;
        DBUtil dbUtil = new DBUtil();
        String sql1 = "insert into bill(billdate,billduedate,vendorid,paidflag,sum) values(?,?,?,?,?)";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql1, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = null;
        try {
            ps.setDate(1, bill.getBilldate());
            ps.setDate(2, bill.getBillduedate());
            ps.setInt(3, bill.getVendorid());
            ps.setBoolean(4, bill.isPaidflag());
            ps.setDouble(5,bill.getSum());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                billid = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                dbUtil.getConn().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return billid;
    }

    @Override
    public boolean addBilldetails(List<Billdetails> billdetailsList) {
        boolean result = false;
        DBUtil dbUtil = new DBUtil();
        try {
            dbUtil.getConn().setAutoCommit(false); //关闭自动提交
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "insert into billdetails(billid,departid,amount) values(?,?,?)";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        for (Billdetails bills :
                billdetailsList) {
            try {
                ps.setInt(1,bills.getId().getBillid());
                ps.setInt(2,bills.getId().getDepartid());
                ps.setDouble(3,bills.getAmount());
                ps.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            result = ps.executeBatch().length != 0;
            dbUtil.getConn().commit();
            dbUtil.getConn().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                dbUtil.getConn().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            CloseStream.close(ps);
        }
        dbUtil.close();
        return result;
    }

    @Override
    public List<Bill> selectNotPaidBillListByVendorid(int  vendorid) {
        List<Bill> billList = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = "select billid,billdate,billduedate,sum from bill where paidflag = false and vendorid = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1, vendorid);
            rs = ps.executeQuery();
            while (rs.next()){
                Bill bill = new Bill();
                bill.setBillid(rs.getInt("billid"));
                bill.setVendorid(vendorid);
                bill.setBilldate(rs.getDate("billdate"));
                bill.setBillduedate(rs.getDate("billduedate"));
                bill.setSum(rs.getDouble("sum"));
                bill.setPaidflag(false);
                billList.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return billList;
    }

    @Override
    public List<Billdetails> selectBillDetailsListByBillId(int billid) {
        List<Billdetails> list = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = "select departname,sum(amount) amount from billdetails natural join department where billid=? group by departid";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1, billid);
            rs = ps.executeQuery();
            while (rs.next()){
                Billdetails billdetails = new Billdetails();
                billdetails.setDepartname(rs.getString("departname"));
                billdetails.setAmount(rs.getDouble("amount"));
                list.add(billdetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return list;
    }

    @Override
    public Bill selectBillById(int billid) {
        Bill bill = new Bill();
        DBUtil dbUtil = new DBUtil();
        String sql = "select billdate,billduedate,vendorid,vendorname,sum from bill natural join vendor where billid = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setInt(1,billid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                bill.setBillid(billid);
                bill.setVendorid(rs.getInt("vendorid"));
                bill.setBilldate(rs.getDate("billdate"));
                bill.setBillduedate(rs.getDate("billduedate"));
                bill.setVendorname(rs.getString("vendorname"));
                bill.setSum(rs.getDouble("sum"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return bill;
    }

    @Override
    public boolean updateBillWithPaidFlag(int billid) {
        boolean result = false;
        DBUtil dbUtil = new DBUtil();
        String sql = "update bill set paidflag = true where billid = ?";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setInt(1,billid);
            result = ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return result;
    }

    @Override
    public boolean addPayment(Payment payment) {
        boolean result = false;
        DBUtil dbUtil = new DBUtil();
        String sql = "insert into payment(billid,paymode,checkno) values(?,?,?)";
        PreparedStatement ps = dbUtil.getPrepareparedStatement(sql);
        try {
            ps.setInt(1,payment.getBillid());
            ps.setInt(2,payment.getPaymode());
            ps.setString(3,payment.getCheckno());
            result = ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUtil.close();
        return result;
    }
}
