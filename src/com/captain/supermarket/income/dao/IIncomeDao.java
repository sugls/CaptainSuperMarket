package com.captain.supermarket.income.dao;

import com.captain.supermarket.income.vo.Income;
import com.captain.supermarket.util.IBaseDao;

import java.sql.Date;
import java.util.List;

/**
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
     * @param income
     * @return 是否添加成功
     */
    boolean addIncome(Income income);

    /**
     *
     * @param incomeid
     * @return
     */
    Income getIncomeById(int incomeid);

    /**
     * update
     * @param income
     * @return
     */
    boolean updateIncome(Income income);
}
