package com.captain.supermarket.bill.dao;

import com.captain.supermarket.bill.vo.Bill;
import com.captain.supermarket.bill.vo.Billdetails;
import com.captain.supermarket.bill.vo.Payment;
import com.captain.supermarket.util.IBaseDao;

import java.util.List;

/**
 * @author lsc
 *         createtime 2017年 02月 09日 星期四 下午5:25
 */
public interface IBillDao extends IBaseDao{
    /**
     * 插入数据到账单表
     * @param bill 账单对象
     * @return 若插入成功返回刚插入记录的主键billid，否则返回-1
     */
    int addBill(Bill bill);

    Bill selectBillById(int billid);

    boolean addBilldetails(List<Billdetails> billdetailsList);

    List<Bill> selectNotPaidBillListByVendorid(int vendorid);

    List<Billdetails> selectBillDetailsListByBillId(int billid);

    boolean updateBillWithPaidFlag(int billid);

    boolean addPayment(Payment payment);
}
