package com.captain.supermarket.manage.dao;

import com.captain.supermarket.manage.vo.Report;
import com.captain.supermarket.util.IBaseDao;

import java.sql.Date;
import java.util.List;

/**
 * 系统业务 接口
 * @author lsc
 *         createtime 2017年 02月 06日 星期一 下午12:54
 */
public interface IManageDao extends IBaseDao{
    /**
     * 获取一段时间某部门收入支出报表信息
     * @param fromdate 开始日期
     * @param todate 结束日期
     * @param departname 部门名称
     * @return 报表对象 List
     */
    List<Report> getReportsByDateRange(Date fromdate,Date todate,String departname);

    /**
     * 获取该数据库所有表名
     * @return 数据库表名集合
     */
    List<String> selectAllTableName();

}
