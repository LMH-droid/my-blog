package com.yourname.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 销毁session
        HttpSession session = request.getSession();
        session.invalidate();

        // 跳转到首页
        response.sendRedirect("home");
    }
}