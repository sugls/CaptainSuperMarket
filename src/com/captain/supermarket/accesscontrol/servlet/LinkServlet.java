package com.captain.supermarket.accesscontrol.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lsc
 *         createtime 2017年 02月 03日 星期五 下午8:47
 */
@WebServlet(name = "LinkServlet",urlPatterns = "/link")
public class LinkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String option = request.getParameter("option");
        HttpSession session = request.getSession();
        String url = null;
        if ("income".equals(option)){
            url = "jsp/incomeMain.jsp";
        } else if ("depart".equals(option)){
            url = "jsp/departmentMain.jsp";
        } else if ("report".equals(option)){
            url = "jsp/Report.jsp";
        } else if ("backup".equals(option)){
            url = "jsp/Backup.jsp";
        } else if ("vendor".equals(option)){
            url = "jsp/vendorMain.jsp";
        } else if ("bill".equals(option)){
            url = "jsp/billAdd.jsp";
        } else if ("payment".equals(option)){
            url = "jsp/billPaymentMain.jsp";
        } else if ("exit".equals(option)){
            session.removeAttribute("user");
            url = "index.jsp";
        }
        request.getRequestDispatcher(url).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
