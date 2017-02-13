package com.captain.supermarket.bill.servlet;

import com.captain.supermarket.bill.dao.IBillDao;
import com.captain.supermarket.bill.vo.Bill;
import com.captain.supermarket.bill.vo.Billdetails;
import com.captain.supermarket.bill.vo.BilldetailsPK;
import com.captain.supermarket.bill.vo.Payment;
import com.captain.supermarket.depart.dao.IDepartDao;
import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.util.DaoFactory;
import com.captain.supermarket.vendor.dao.IVendorDao;
import com.captain.supermarket.vendor.vo.Vendor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Vector;

/**
 * @author lsc
 *         createtime 2017年 02月 08日 星期三 下午5:02
 */
@WebServlet(name = "BillServlet",urlPatterns = "/bill")
public class BillServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        String url = null;
        HttpSession session = request.getSession();
        IVendorDao vendorDao = (IVendorDao) DaoFactory.getInstance("vendor");
        IDepartDao departDao = (IDepartDao) DaoFactory.getInstance("depart");
        IBillDao billDao = (IBillDao) DaoFactory.getInstance("bill");
        if ("add".equals(option)){
            List<Vendor> listvendor = (List<Vendor>) session.getAttribute("listvendor");
            if (listvendor==null){
                listvendor = vendorDao.getVendorList();
                session.setAttribute("listvendor",listvendor);
            }
            url = "jsp/billAdd.jsp";
        } else if ("additem".equals(option)){
            Bill bill = new Bill();
            String billdate = request.getParameter("billdate");
            String duedate = request.getParameter("duedate");
            String vendorid = request.getParameter("vendor");
            String name = vendorDao.getVendorById(Integer.parseInt(vendorid)).getVendorname();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                bill.setBilldate(new Date(sdf.parse(billdate).getTime()));
                bill.setBillduedate(new Date(sdf.parse(duedate).getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bill.setVendorid(Integer.parseInt(vendorid));
            bill.setVendorname(name);
            bill.setPaidflag(false);
            List<Department> listdepart = (List<Department>) session.getAttribute("listdepart");
            if (listdepart==null){
                listdepart = departDao.getDepart();
                session.setAttribute("listdepart",listdepart);
            }
            session.setAttribute("bill",bill);
            url = "jsp/billAddDepItem.jsp";
        } else if ("adddepitem".equals(option)){
            Bill bill = (Bill) session.getAttribute("bill");
            String sum = request.getParameter("sum");
            bill.setSum(Double.parseDouble(sum.substring(0,sum.indexOf("$"))));
            System.out.println(bill);
            int billid = billDao.addBill(bill);
            if (billid!=-1) {
                List<Billdetails> billdetailsList = new Vector<>();
                String[] values = request.getParameterValues("confirm");
                for (String s :
                        values) {
                    String[] id = s.split(",");
                    Billdetails billdetails = new Billdetails();
                    billdetails.setId(new BilldetailsPK(billid,Integer.parseInt(id[0])));
                    billdetails.setAmount(Double.parseDouble(id[1]));
                    billdetailsList.add(billdetails);
                }
                if (billDao.addBilldetails(billdetailsList)){
                    request.setAttribute("msg","add successfully");
                    url = "jsp/billAdd.jsp";
                }
            }
        } else if ("payment".equals(option)){
            List<Vendor> listvendor = (List<Vendor>) session.getAttribute("listvendor");
            if (listvendor==null){
                listvendor = vendorDao.getVendorList();
                session.setAttribute("listvendor",listvendor);
            }
            url = "jsp/billPaymentMain.jsp";
        } else if ("billpayment".equals(option)){
            String vendor = request.getParameter("vendorname");
            int vendorid = Integer.parseInt(vendor.substring(0,vendor.indexOf("/")));
            String vendorname = vendor.substring(vendor.indexOf("/")+1);
            List<Bill> billList = billDao.selectNotPaidBillListByVendorid(vendorid);
            request.setAttribute("vendorname",vendorname);
            request.setAttribute("billlist",billList);
            url = "jsp/billPayment.jsp";
        } else if ("pay".equals(option)){
            String billid = request.getParameter("id");
            List<Billdetails> billdetailsList = new Vector<>();
            billdetailsList = billDao.selectBillDetailsListByBillId(Integer.parseInt(billid));
            request.setAttribute("listdetails",billdetailsList);
            request.setAttribute("payment",request.getParameter("payment"));
            request.setAttribute("bill",billDao.selectBillById(Integer.parseInt(billid)));
            url = "jsp/payment.jsp";
        } else if ("dopay".equals(option)){
            int billid = Integer.parseInt(request.getParameter("id"));
            int paymode = Integer.parseInt(request.getParameter("payment"));
            Payment payment = new Payment();
            if (paymode==2){
                String checkno = request.getParameter("checkno");
                payment.setCheckno(checkno);
            }
            payment.setBillid(billid);
            payment.setPaymode(paymode);
            if (billDao.updateBillWithPaidFlag(billid) && billDao.addPayment(payment)){
                Bill bill = billDao.selectBillById(billid);
                String vendorname = bill.getVendorid()+"/"+bill.getVendorname();
                request.setAttribute("msg","pay successfully");
                url = "bill?option=billpayment&vendorname="+vendorname;
            }
        }
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
