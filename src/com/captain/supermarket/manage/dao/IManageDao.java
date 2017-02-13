package com.captain.supermarket.manage.dao;

import com.captain.supermarket.manage.vo.Report;
import com.captain.supermarket.util.IBaseDao;

import java.sql.Date;
import java.util.List;

/**
 * @author lsc
 *         createtime 2017年 02月 06日 星期一 下午12:54
 */
public interface IManageDao extends IBaseDao{
    List<Report> getReportsByDateRange(Date fromdate,Date todate,String departname);

    List<String> selectAllTableName();

}
