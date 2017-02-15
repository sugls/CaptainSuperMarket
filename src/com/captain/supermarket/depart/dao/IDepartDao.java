package com.captain.supermarket.depart.dao;

import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.util.IBaseDao;

import java.util.List;

/**
 * 部门相关业务接口
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 下午12:34
 */
public interface IDepartDao extends IBaseDao{
    /**
     * 获取部门集合
     * @return 部门 List
     */
    List<Department> getDepart();

    /**
     * 添加部门
     * @param depart 对象
     * @return 添加成功返回 “successful” ; 添加部门已存在返回 “this department already exists” ; 其他返回 null
     */
    String addDepart(Department depart);

    /**
     * 通过部门 id 获取部门对象
     * @param departid 部门 id
     * @return Department 对象
     */
    Department getDepartById(int departid);

    /**
     * 更新部门信息
     * @param depart 部门对象
     * @return 更新成功返回 “successful”; 新部门已存在返回 “this department is already exist”;其他返回 null
     */
    String updateDepart(Department depart);
}
