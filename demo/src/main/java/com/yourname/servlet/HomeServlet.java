package com.yourname.servlet;

import com.yourname.dao.BlogDao;
import com.yourname.entity.Blog;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("=== HomeServlet开始执行 ===");

        try {
            // 1. 从数据库获取博客数据
            BlogDao blogDao = new BlogDao();
            List<Blog> blogs = blogDao.getAllBlogs();

            System.out.println("从数据库获取到的博客数量: " + (blogs != null ? blogs.size() : "null"));

            // 2. 将数据放入request范围
            request.setAttribute("blogs", blogs);
            System.out.println("已将blogs数据存入request");

            // 3. 跳转到index.jsp页面
            System.out.println("准备转发到index.jsp");
            request.getRequestDispatcher("/index.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("HomeServlet出现异常: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("=== HomeServlet执行完成 ===");
    }
}