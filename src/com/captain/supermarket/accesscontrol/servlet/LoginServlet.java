package com.captain.supermarket.accesscontrol.servlet;

import com.captain.supermarket.accesscontrol.dao.IUserDao;
import com.captain.supermarket.accesscontrol.dao.UserDaoImpl;
import com.captain.supermarket.accesscontrol.vo.Users;
import com.captain.supermarket.util.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户相关业务控制器
 * @author lsc
 *         createtime 2017年 02月 03日 星期五 上午11:32
 */
@WebServlet(name = "LoginServlet",urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("passwd");
        HttpSession session = request.getSession();
        IUserDao userDao = (IUserDao) DaoFactory.getInstance("user");
        Users user = userDao.checkLogin(username,password);
        String url;
        if (user!=null){
            session.setAttribute("user",user);
            url = "jsp/description.jsp";
        } else {
            request.setAttribute("error","oops,check your username or password!");
            url = "index.jsp";
        }
        request.getRequestDispatcher(url).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
