package com.captain.supermarket.util;

import com.captain.supermarket.accesscontrol.dao.UserDaoImpl;
import com.captain.supermarket.bill.dao.BillDaoImpl;
import com.captain.supermarket.depart.dao.DepartDaoImpl;
import com.captain.supermarket.income.dao.IncomeDaoImpl;
import com.captain.supermarket.manage.dao.ManageDaoImpl;
import com.captain.supermarket.vendor.dao.VendorDaoImpl;

/**
 * 实例工厂类
 * @author lsc
 *         createtime 2017年 02月 02日 星期四 下午8:56
 */
public class DaoFactory {

    public static IBaseDao getInstance(String name){
        if ("user".equals(name)){
          return  new UserDaoImpl();
        } else if ("income".equals(name)){
            return new IncomeDaoImpl();
        } else if ("depart".equals(name)){
            return DepartDaoImpl.INSTANCE;
        } else if ("manage".equals(name)){
            return new ManageDaoImpl();
        } else if ("vendor".equals(name)){
            return new VendorDaoImpl();
        } else if ("bill".equals(name)){
            return new BillDaoImpl();
        }
        else {
            return null;
        }
    }

}
