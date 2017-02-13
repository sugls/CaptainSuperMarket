package com.captain.supermarket.vendor.dao;

import com.captain.supermarket.util.IBaseDao;
import com.captain.supermarket.vendor.vo.Vendor;

import java.util.List;

/**
 * @author lsc
 *         createtime 2017年 02月 07日 星期二 下午1:30
 */
public interface IVendorDao extends IBaseDao{
    List<Vendor> getVendorList();

    boolean  hasVendorByName(String vendorname);

    boolean addVendor(Vendor vendor);

    Vendor getVendorById(int vendorid);

    boolean updateVendor(Vendor vendor);
}
