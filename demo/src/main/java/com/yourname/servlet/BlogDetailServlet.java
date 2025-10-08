package com.yourname.servlet;

import com.yourname.dao.BlogDao;
import com.yourname.entity.Blog;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/blogDetail")
public class BlogDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        try {
            // 获取博客ID参数
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.isEmpty()) {
                response.sendRedirect("home");
                return;
            }

            int blogId = Integer.parseInt(idParam);
            BlogDao blogDao = new BlogDao();
            Blog blog = blogDao.getBlogById(blogId);

            if (blog != null) {
                request.setAttribute("blog", blog);
                request.getRequestDispatcher("/blog_detail.jsp").forward(request, response);
            } else {
                // 博客不存在，跳回首页
                response.sendRedirect("home");
            }

        } catch (NumberFormatException e) {
            // ID参数格式错误
            response.sendRedirect("home");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("home");
        }
    }
}