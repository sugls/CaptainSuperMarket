package com.captain.supermarket.vendor.servlet;

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
import java.util.List;

/**
 * 供应商相关业务控制器
 * @author lsc
 *         createtime 2017年 02月 07日 星期二 下午1:32
 */
@WebServlet(name = "VendorServlet",urlPatterns = "/vendor")
public class VendorServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        String url = null;
        HttpSession session = request.getSession();
        IVendorDao vendorDao = (IVendorDao) DaoFactory.getInstance("vendor");
        if ("show".equals(option)){
            List<Vendor> listvendor = (List<Vendor>) session.getAttribute("listvendor");
            if (listvendor==null){
                listvendor = vendorDao.getVendorList();
                session.setAttribute("listvendor",listvendor);
            }
            url = "jsp/vendorMain.jsp";
        } else if ("add".equals(option)){
            url = "jsp/vendorAdd.jsp";
        } else if ("addvendor".equals(option)){
            String vendorname = request.getParameter("vendorname");
            if (vendorDao.hasVendorByName(vendorname)){
                request.setAttribute("msg",vendorname+" already exist,"+"please add another vendor or choose vendor management to update it");
                url = "jsp/vendorAdd.jsp";
            } else {
                Vendor vendor = new Vendor();
                vendor.setVendorname(vendorname);
                vendor.setVendoraddress(request.getParameter("vendoraddress"));
                vendor.setVendorphone(request.getParameter("vendorphone"));
                vendor.setVendorfax(request.getParameter("vendorfax"));
                vendor.setContact(request.getParameter("contact"));
                if (vendorDao.addVendor(vendor)){
                    request.setAttribute("msg","add successfully");
                    session.removeAttribute("listvendor");
                    url = "vendor?option=show";
                }
            }
        } else if ("update".equals(option)){
            String id = request.getParameter("id");
            request.setAttribute("vendor",vendorDao.getVendorById(Integer.parseInt(id)));
            url = "jsp/vendorUpdate.jsp";
        } else if ("updatevendor".equals(option)){
            Vendor vendor = new Vendor();
            String flag = request.getParameter("flag");
            String name = request.getParameter("vendorname");
            String vendorid = request.getParameter("id");
            vendor.setVendorname(name);
            vendor.setVendorid(Integer.parseInt(vendorid));
            vendor.setVendoraddress(request.getParameter("vendoraddress"));
            vendor.setVendorphone(request.getParameter("vendorphone"));
            vendor.setVendorfax(request.getParameter("vendorfax"));
            vendor.setContact(request.getParameter("contact"));
            if ("true".equals(flag)){
                if (vendorDao.hasVendorByName(name)){
                    request.setAttribute("msg","your new vendor "+name+" already exists ,please check it");
                    request.setAttribute("vendor",vendorDao.getVendorById(Integer.parseInt(vendorid)      ));
                    url = "jsp/vendorUpdate.jsp";
                } else {
                    if (vendorDao.updateVendor(vendor)){
                        request.setAttribute("msg","update successfully");
                        session.removeAttribute("listvendor");
                        url = "vendor?option=show";
                    }
                }
            } else {
                if (vendorDao.updateVendor(vendor)){
                    request.setAttribute("msg","update successfully");
                    session.removeAttribute("listvendor");
                    url = "vendor?option=show";
                }
            }
        }
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
