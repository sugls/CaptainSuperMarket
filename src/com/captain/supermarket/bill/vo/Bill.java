package com.captain.supermarket.bill.vo;

import java.sql.Date;

/**
 * 账单 vo
 * @author lsc
 *         createtime 2017年 02月 09日 星期四 上午10:59
 */
public class Bill {
    private int billid;
    private Date billdate;
    private Date billduedate;
    private int vendorid;
    private String vendorname;
    private boolean paidflag;
    private double sum;

    public Bill() {
    }

    public Bill(int billid, Date billdate, Date billduedate, int vendorid, String vendorname, boolean paidflag, double sum) {
        this.billid = billid;
        this.billdate = billdate;
        this.billduedate = billduedate;
        this.vendorid = vendorid;
        this.vendorname = vendorname;
        this.paidflag = paidflag;
        this.sum = sum;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public Date getBilldate() {
        return billdate;
    }

    public void setBilldate(Date billdate) {
        this.billdate = billdate;
    }

    public Date getBillduedate() {
        return billduedate;
    }

    public void setBillduedate(Date billduedate) {
        this.billduedate = billduedate;
    }

    public int getVendorid() {
        return vendorid;
    }

    public void setVendorid(int vendorid) {
        this.vendorid = vendorid;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public boolean isPaidflag() {
        return paidflag;
    }

    public void setPaidflag(boolean paidflag) {
        this.paidflag = paidflag;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billid=" + billid +
                ", billdate=" + billdate +
                ", billduedate=" + billduedate +
                ", vendorid=" + vendorid +
                ", vendorname='" + vendorname + '\'' +
                ", paidflag=" + paidflag +
                ", sum=" + sum +
                '}';
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
