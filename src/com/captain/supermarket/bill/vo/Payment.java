package com.captain.supermarket.bill.vo;

/**
 * 支付信息
 * @author lsc
 *         createtime 2017年 02月 10日 星期五 下午4:11
 */
public class Payment {
    private int payid;
    private int billid;
    private int paymode;
    private String checkno;

    public Payment() {
    }

    public Payment(int payid, int billid, int paymode, String checkno) {
        this.payid = payid;
        this.billid = billid;
        this.paymode = paymode;
        this.checkno = checkno;
    }

    public int getPayid() {
        return payid;
    }

    public void setPayid(int payid) {
        this.payid = payid;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public int getPaymode() {
        return paymode;
    }

    public void setPaymode(int paymode) {
        this.paymode = paymode;
    }

    public String getCheckno() {
        return checkno;
    }

    public void setCheckno(String checkno) {
        this.checkno = checkno;
    }
}
