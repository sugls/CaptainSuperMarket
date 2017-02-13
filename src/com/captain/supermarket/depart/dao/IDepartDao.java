package com.captain.supermarket.depart.dao;

import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.util.IBaseDao;

import java.util.List;

/**
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 下午12:34
 */
public interface IDepartDao extends IBaseDao{
    /**
     * 获取部门集合
     * @return
     */
    List<Department> getDepart();

    /**
     * 添加部门
     * @param depart
     * @return
     */
    String addDepart(Department depart);

    Department getDepartById(int departid);

    /**
     *
     * @param depart
     * @return
     */
    String updateDepart(Department depart);
}
