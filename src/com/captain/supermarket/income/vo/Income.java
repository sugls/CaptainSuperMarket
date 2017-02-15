package com.captain.supermarket.income.vo;

import java.sql.Date;

/**
 * 收入 vo
 * @author lsc
 *         createtime 2017年 02月 04日 星期六 上午10:57
 */
public class Income {
    private int incomeid;
    private int departid;
    private Date businessdate;
    private Date lastmoddate;
    private Double dailyincome;
    private String departname;

    public Income() {
    }

    public Income(int incomeid, int departid, Date businessdate, Date lastmoddate, Double dailyincome, String departname) {
        this.incomeid = incomeid;
        this.departid = departid;
        this.businessdate = businessdate;
        this.lastmoddate = lastmoddate;
        this.dailyincome = dailyincome;
        this.departname = departname;
    }

    public int getIncomeid() {
        return incomeid;
    }

    public void setIncomeid(int incomeid) {
        this.incomeid = incomeid;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }

    public Date getBusinessdate() {
        return businessdate;
    }

    public void setBusinessdate(Date businessdate) {
        this.businessdate = businessdate;
    }

    public Date getLastmoddate() {
        return lastmoddate;
    }

    public void setLastmoddate(Date lastmoddate) {
        this.lastmoddate = lastmoddate;
    }

    public Double getDailyincome() {
        return dailyincome;
    }

    public void setDailyincome(Double dailyincome) {
        this.dailyincome = dailyincome;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }
}
