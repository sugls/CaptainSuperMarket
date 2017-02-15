package com.captain.supermarket.bill.dao;

import com.captain.supermarket.bill.vo.Bill;
import com.captain.supermarket.bill.vo.Billdetails;
import com.captain.supermarket.bill.vo.Payment;
import com.captain.supermarket.util.IBaseDao;

import java.util.List;

/**
 * 账单相关业务接口
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

    /**
     * 通过账单 id 查询账单信息
     * @param billid 账单 id
     * @return 账单对象
     */
    Bill selectBillById(int billid);

    /**
     * 批量插入一条账单对应的账单详情
     * @param billdetailsList 账单详情列表
     * @return 成功或失败
     */
    boolean addBilldetails(List<Billdetails> billdetailsList);

    /**
     * 通过供应商 id 查询对该供应商还未支付的账单列表
     * @param vendorid 供应商 id
     * @return 未支付账单列表
     */
    List<Bill> selectNotPaidBillListByVendorid(int vendorid);

    /**
     * 通过账单 id 查询该账单对应的账单详情列表
     * @param billid 账单 id
     * @return 账单详情 List
     */
    List<Billdetails> selectBillDetailsListByBillId(int billid);

    /**
     * 更新账单支付标记为已支付
     * @param billid 账单 id
     * @return true or false
     */
    boolean updateBillWithPaidFlag(int billid);

    /**
     * 插入一条支付信息到 payment 表
     * @param payment 支付信息对象
     * @return 布尔值
     */
    boolean addPayment(Payment payment);
}
