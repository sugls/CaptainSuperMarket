package com.captain.supermarket.income.dao;

import com.captain.supermarket.income.vo.Income;
import com.captain.supermarket.util.IBaseDao;

import java.sql.Date;
import java.util.List;

/**
 * 输入相关业务接口
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 上午11:02
 */
public interface IIncomeDao extends IBaseDao{
    /**
     * 根据日期获取收入列表
     * @param date 日期
     * @return Income 集合
     */
    List<Income> getIncomeByDate(Date date);

    /**
     * 添加收入
     * @param income income 对象
     * @return 是否添加成功
     */
    boolean addIncome(Income income);

    /**
     * 通过收入 id 获取一条 income 对象
     * @param incomeid 收入 id
     * @return Income
     */
    Income getIncomeById(int incomeid);

    /**
     * 更新收入信息
     * @param income income 对象
     * @return 布尔值
     */
    boolean updateIncome(Income income);
}
