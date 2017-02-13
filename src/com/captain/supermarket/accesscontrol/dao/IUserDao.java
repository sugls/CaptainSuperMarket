package com.captain.supermarket.accesscontrol.dao;

import com.captain.supermarket.accesscontrol.vo.Users;
import com.captain.supermarket.util.IBaseDao;

/**
 * @author lsc
 *         createtime 2017年 02月 02日 星期四 下午9:01
 */
public interface IUserDao extends IBaseDao{
    /**
     * 登录验证
     * @param username
     * @param password
     * @return Users
     */
    Users checkLogin(String username,String password);
}
