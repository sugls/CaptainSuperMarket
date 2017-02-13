package com.captain.supermarket.bill.vo;

/**
 * @author lsc
 *         createtime 2017年 02月 09日 星期四 上午11:08
 */
public class Billdetails {
    private BilldetailsPK id;
    private double amount;
    private String departname;

    public Billdetails() {
    }

    public Billdetails(int billid, int departid, double amount, String departname) {
        this.departname = departname;
        this.id = new BilldetailsPK(billid,departid);
        this.amount = amount;
    }

    public BilldetailsPK getId() {
        return id;
    }

    public void setId(BilldetailsPK id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }
}
