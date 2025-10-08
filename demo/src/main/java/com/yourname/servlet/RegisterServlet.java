package com.yourname.servlet;

import com.yourname.dao.UserDao;
import com.yourname.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 跳转到注册页面
        request.getRequestDispatcher("/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // 获取表单参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        UserDao userDao = new UserDao();

        // 检查用户名是否已存在
        if (userDao.findByUsername(username) != null) {
            request.setAttribute("error", "用户名已存在！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        // 创建新用户
        User user = new User(username, password, email);
        boolean success = userDao.addUser(user);

        if (success) {
            // 注册成功，跳转到登录页面（避免中文用英文参数）
            response.sendRedirect("login.jsp?message=success");
        } else {
            // 注册失败
            request.setAttribute("error", "注册失败，请重试！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
        }
    }
}