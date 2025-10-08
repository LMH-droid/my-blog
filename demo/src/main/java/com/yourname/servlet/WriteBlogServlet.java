package com.yourname.servlet;

import com.yourname.dao.BlogDao;
import com.yourname.entity.Blog;
import com.yourname.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/writeBlog")
public class WriteBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        request.getRequestDispatcher("/write_blog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        System.out.println("=== 写博客调试 ===");
        System.out.println("Session用户: " + (user != null ? user.getUsername() : "null"));
        System.out.println("Session用户ID: " + (user != null ? user.getId() : "null"));

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String title = request.getParameter("title");
        String content = request.getParameter("content");

        if (title == null || title.trim().isEmpty() || content == null || content.trim().isEmpty()) {
            request.setAttribute("error", "标题和内容不能为空！");
            request.getRequestDispatcher("/write_blog.jsp").forward(request, response);
            return;
        }

        Blog blog = new Blog(title.trim(), content.trim(), user.getId());
        BlogDao blogDao = new BlogDao();
        boolean success = blogDao.addBlog(blog);

        System.out.println("发布结果: " + success);

        if (success) {
            response.sendRedirect("home");
        } else {
            request.setAttribute("error", "发布失败，请重试！");
            request.getRequestDispatcher("/write_blog.jsp").forward(request, response);
        }
    }
}