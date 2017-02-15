package com.captain.supermarket.accesscontrol.dao;

import com.captain.supermarket.accesscontrol.vo.Users;
import com.captain.supermarket.util.IBaseDao;

/**
 * 用户相关业务接口
 * @author lsc
 *         createtime 2017年 02月 02日 星期四 下午9:01
 */
public interface IUserDao extends IBaseDao{
    /**
     * 登录验证
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回该 User 对象，否则返回 null
     */
    Users checkLogin(String username,String password);
}
