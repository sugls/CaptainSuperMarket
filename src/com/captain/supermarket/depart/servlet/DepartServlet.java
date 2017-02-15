package com.captain.supermarket.depart.servlet;

import com.captain.supermarket.depart.dao.IDepartDao;
import com.captain.supermarket.depart.vo.Department;
import com.captain.supermarket.util.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 部门相关业务控制器
 * @author lsc
 *         createtime 2017年 02月 03日 星期五 下午10:22
 */
@WebServlet(name = "DepartServlet",urlPatterns = "/depart")
public class DepartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String option = request.getParameter("option");
        String url = null;
        HttpSession session = request.getSession();
        IDepartDao departDao = (IDepartDao) DaoFactory.getInstance("depart");
        if ("add".equals(option)){
            url = "jsp/departmentAdd.jsp";
        } else if ("show".equals(option)){
            List<Department> listdepart = (List<Department>) session.getAttribute("listdepart");
            if (listdepart==null){
                listdepart = departDao.getDepart();
                session.setAttribute("listdepart",listdepart);
            }
            url = "jsp/departmentMain.jsp";
        } else if ("adddepart".equals(option)){
            String departname = request.getParameter("departname");
            String description = request.getParameter("description");
            Department depart = new Department();
            depart.setDepartname(departname);
            depart.setDescription(description);
            String msg = departDao.addDepart(depart);
            if ("successful".equals(msg)){
                url = "depart?option=show";
                session.removeAttribute("listdepart");
            } else {
                request.setAttribute("msg",msg);
                url = "depart?option=add";
            }
        } else if ("update".equals(option)){
            Department depart = departDao.getDepartById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("depart",depart);
            url = "jsp/departmentUpdate.jsp";
        } else if ("updatedepart".equals(option)){
            String departid = request.getParameter("id");
            String departname = request.getParameter("departname");
            String description = request.getParameter("description");
            Department depart = new Department();
            depart.setDepartid(Integer.parseInt(departid));
            depart.setDepartname(departname);
            depart.setDescription(description);
            if ("successful".equals(departDao.updateDepart(depart))){
                url = "depart?option=show";
                request.setAttribute("msg","update successfully");
                session.removeAttribute("listdepart");
            } else {
                request.setAttribute("msg",departDao.updateDepart(depart));
                url = "depart?option=update";
            }

        }
        request.getRequestDispatcher(url).forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
