package com.captain.supermarket.vendor.dao;

import com.captain.supermarket.util.CloseStream;
import com.captain.supermarket.util.DBUtil;
import com.captain.supermarket.vendor.vo.Vendor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

/**
 * @author lsc
 *         createtime 2017年 02月 07日 星期二 下午1:30
 */
public class VendorDaoImpl implements IVendorDao{
    @Override
    public List<Vendor> getVendorList() {
        List<Vendor> listvendor = new Vector<>();
        DBUtil dbUtil = new DBUtil();
        String sql = " select vendorid,vendorname,vendoraddress,vendorphone,vendorfax,contact from vendor";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            rs = ps.executeQuery();
            while (rs.next()) {
                Vendor vendor = new Vendor();
                vendor.setVendorid(rs.getInt("vendorid"));
                vendor.setVendorname(rs.getString("vendorname"));
                vendor.setVendoraddress(rs.getString("vendoraddress"));
                vendor.setVendorphone(rs.getString("vendorphone"));
                vendor.setVendorfax(rs.getString("vendorfax"));
                vendor.setContact(rs.getString("contact"));
                listvendor.add(vendor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return listvendor;
    }

    @Override
    public boolean hasVendorByName(String vendorname) {
        DBUtil dbUtil = new DBUtil();
        String sql = "select vendorid from vendor where vendorname = ?";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        boolean result = false;
        ResultSet rs = null;
        try {
            ps.setString(1, vendorname);
            rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return result;
    }

    @Override
    public boolean addVendor(Vendor vendor) {
        boolean result = false;
        DBUtil dbUtil = new DBUtil();
        String sql = "insert into vendor(vendorname,vendoraddress,vendorphone,vendorfax,contact) values(?,?,?,?,?)";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        try {
            ps.setString(1,vendor.getVendorname());
            ps.setString(2,vendor.getVendoraddress());
            ps.setString(3,vendor.getVendorphone());
            ps.setString(4,vendor.getVendorfax());
            ps.setString(5,vendor.getContact());
            result = ps.executeUpdate() != 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(ps);
        }
        dbUtil.close();
        return result;
    }

    @Override
    public Vendor getVendorById(int vendorid) {
        Vendor vendor = new Vendor();
        DBUtil dbUtil = new DBUtil();
        String sql = "select vendorname,vendoraddress,vendorphone,vendorfax,contact from vendor where vendorid = ?";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        ResultSet rs = null;
        try {
            ps.setInt(1, vendorid);
            rs = ps.executeQuery();
            if (rs.next()) {
                vendor.setVendorid(vendorid);
                vendor.setVendorname(rs.getString("vendorname"));
                vendor.setVendoraddress(rs.getString("vendoraddress"));
                vendor.setVendorphone(rs.getString("vendorphone"));
                vendor.setVendorfax(rs.getString("vendorfax"));
                vendor.setContact(rs.getString("contact"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(rs, ps);
        }
        dbUtil.close();
        return vendor;
    }

    @Override
    public boolean updateVendor(Vendor vendor) {
        boolean result = false;
        DBUtil dbUtil = new DBUtil();
        String sql = "update vendor set vendorname = ?,vendoraddress = ?,vendorphone = ?,vendorfax = ?,contact = ? where vendorid = ?";
        PreparedStatement ps = dbUtil.getPreparedStatement(sql);
        try {
            ps.setString(1,vendor.getVendorname());
            ps.setString(2,vendor.getVendoraddress());
            ps.setString(3,vendor.getVendorphone());
            ps.setString(4,vendor.getVendorfax());
            ps.setString(5,vendor.getContact());
            ps.setInt(6,vendor.getVendorid());
            result = ps.executeUpdate() !=0 ;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            CloseStream.close(ps);
        }
        dbUtil.close();
        return result;
    }
}
