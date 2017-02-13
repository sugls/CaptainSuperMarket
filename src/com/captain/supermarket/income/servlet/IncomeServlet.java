package com.captain.supermarket.income.servlet;

import com.captain.supermarket.depart.dao.IDepartDao;
import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.income.dao.IIncomeDao;
import com.captain.supermarket.income.vo.Income;
import com.captain.supermarket.util.DaoFactory;

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

/**
 * @author lsc
 *         createtime 2017年 02月 03日 星期五 下午9:59
 */
@WebServlet(name = "IncomeServlet",urlPatterns = "/income")
public class IncomeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        String url = null;
        HttpSession session = request.getSession();
        IIncomeDao incomeDao = (IIncomeDao) DaoFactory.getInstance("income");
        IDepartDao departDao = (IDepartDao) DaoFactory.getInstance("depart");
        if ("show".equals(option)){
            String date = request.getParameter("date");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Income> listincome = (List<Income>) session.getAttribute("listincome");
            try {
                Date showdate = new Date(sdf.parse(date).getTime());
                listincome = incomeDao.getIncomeByDate(showdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            request.setAttribute("date",date);
            session.setAttribute("listincome",listincome);
            url = "jsp/incomeMain.jsp";
        } else if ("add".equals(option)){
            List<Department> listdepart = (List<Department>) session.getAttribute("listdepart");
            if (listdepart==null){
                listdepart = departDao.getDepart();
                session.setAttribute("listdepart",listdepart);
            }
            url = "jsp/incomeAdd.jsp";
        } else if ("addincome".equals(option)){
            String departname = request.getParameter("departname");
            String dailyincome = request.getParameter("income");
            Income income = new Income();
            income.setDepartname(departname);
            income.setDailyincome(Double.valueOf(dailyincome));
            boolean result = incomeDao.addIncome(income);
            if (result){
                session.removeAttribute("listincome");
                url = "income?option=show";
            } else {
                url = "jsp/error.jsp";
            }
        } else if ("update".equals(option)){
            Income income = incomeDao.getIncomeById(Integer.parseInt(request.getParameter("id")));
            List<Income> listincome = (List<Income>) session.getAttribute("listincome");
            List<Department> listdepart = (List<Department>) session.getAttribute("listdepart");
            if (listdepart==null){
                listdepart = departDao.getDepart();
                session.setAttribute("listdepart",listdepart);
            }
            if (listincome==null){
                listincome = incomeDao.getIncomeByDate(new Date(System.currentTimeMillis()));
                session.setAttribute("listincome",listincome);
            }
            session.setAttribute("income",income);
            url = "jsp/incomeUpdate.jsp";
        } else if ("updateincome".equals(option)){
            String departid = request.getParameter("departname");
            String dailyincome = request.getParameter("income");
            Income income = (Income) session.getAttribute("income");
            Income newIncome = new Income();
            newIncome.setDailyincome(Double.valueOf(dailyincome));
            newIncome.setBusinessdate(income.getBusinessdate());
            newIncome.setDepartid(Integer.parseInt(departid));
            incomeDao.updateIncome(newIncome);
            session.removeAttribute("listincome");
            url = "income?option=show";
        }
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
