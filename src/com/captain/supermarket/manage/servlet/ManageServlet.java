package com.captain.supermarket.manage.servlet;

import com.captain.supermarket.depart.dao.IDepartDao;
import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.manage.dao.IManageDao;
import com.captain.supermarket.manage.vo.Report;
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
import java.util.Vector;

/**
 * 系统业务控制器
 * 查询报表，备份数据库，注销登录等
 * @author lsc
 *         createtime 2017年 02月 06日 星期一 下午12:38
 */
@WebServlet(name = "ManageServlet",urlPatterns = "/manage")
public class ManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String option = request.getParameter("option");
        HttpSession session = request.getSession();
        String url = null;
        IDepartDao departDao = (IDepartDao) DaoFactory.getInstance("depart");
        IManageDao manageDao = (IManageDao) DaoFactory.getInstance("manage");
        if ("show".equals(option)){
            session.removeAttribute("listreport");
            List<Department> listdepart = (List<Department>) session.getAttribute("listdepart");
            if (listdepart==null){
                listdepart = departDao.getDepart();
                session.setAttribute("listdepart",listdepart);
            }
            url = "jsp/Report.jsp";
        } else if ("report".equals(option)){
            String departname = request.getParameter("departname");
            String fromdate = request.getParameter("fromdate");
            String todate = request.getParameter("todate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            List<Report> listreport = new Vector<>();
            try {
                Date fdate = new Date(sdf.parse(fromdate).getTime());
                Date tdate = new Date(sdf.parse(todate).getTime());
                listreport = manageDao.getReportsByDateRange(fdate,tdate,departname);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            request.setAttribute("fromdate",fromdate);
            request.setAttribute("todate",todate);
            session.setAttribute("listreport",listreport);
            url = "jsp/Report.jsp";
        } else if ("backup".equals(option)){
            List<String> listtable = (List<String>) session.getAttribute("listtable");
            if (listtable==null){
                listtable = manageDao.selectAllTableName();
                session.setAttribute("listtable",listtable);
            }
            url = "jsp/Backup.jsp";
        } else if ("exit".equals(option)){
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect("index.jsp");
            return;
        }
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
