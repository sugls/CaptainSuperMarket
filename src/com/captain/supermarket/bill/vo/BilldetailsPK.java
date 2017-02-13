package com.captain.supermarket.bill.vo;

/**
 * @author lsc
 *         createtime 2017年 02月 09日 星期四 上午11:07
 */
public class BilldetailsPK {
    private int billid;
    private int departid;

    public BilldetailsPK(int billid, int departid) {
        this.billid = billid;
        this.departid = departid;
    }

    public int getBillid() {
        return billid;
    }

    public void setBillid(int billid) {
        this.billid = billid;
    }

    public int getDepartid() {
        return departid;
    }

    public void setDepartid(int departid) {
        this.departid = departid;
    }
}
