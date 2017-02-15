package com.captain.supermarket.vendor.dao;

import com.captain.supermarket.util.IBaseDao;
import com.captain.supermarket.vendor.vo.Vendor;

import java.util.List;

/**
 * 供应商相关业务接口
 * @author lsc
 *         createtime 2017年 02月 07日 星期二 下午1:30
 */
public interface IVendorDao extends IBaseDao{
    /**
     * 获取供应商列表
     * @return 供应商 List
     */
    List<Vendor> getVendorList();

    /**
     * 根据供应商名称查询是否已有该供应商
     * @param vendorname 供应商名称
     * @return 布尔值
     */
    boolean  hasVendorByName(String vendorname);

    /**
     * 添加一条供应商
     * @param vendor 供应商对象
     * @return 布尔值
     */
    boolean addVendor(Vendor vendor);

    /**
     * 通过供应商 id 获取供应商对象
     * @param vendorid 供应商 id
     * @return Vendor 对象
     */
    Vendor getVendorById(int vendorid);

    /**
     * 更新供应商信息
     * @param vendor 供应商对象
     * @return 布尔值
     */
    boolean updateVendor(Vendor vendor);
}
