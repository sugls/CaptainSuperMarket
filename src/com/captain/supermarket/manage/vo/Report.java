package com.captain.supermarket.manage.vo;

import java.sql.Date;

/**
 * 报表 vo
 * @author lsc
 *         createtime 2017年 02月 06日 星期一 下午12:52
 */
public class Report {
    private String departname;
    private Date date;
    private double income;
    private double expense;

    public Report() {
    }

    public Report(String departname, Date date, double income, double expense) {
        this.departname = departname;
        this.date = date;
        this.income = income;
        this.expense = expense;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getExpense() {
        return expense;
    }

    public void setExpense(double expense) {
        this.expense = expense;
    }

    @Override
    public String toString() {
        return "Report{" +
                "departname='" + departname + '\'' +
                ", date=" + date +
                ", income=" + income +
                ", expense=" + expense +
                '}';
    }
}
