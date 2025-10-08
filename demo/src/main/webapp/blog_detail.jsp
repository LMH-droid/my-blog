<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourname.entity.Blog" %>
<%@ page import="com.yourname.entity.User" %>
<%
    Blog blog = (Blog) request.getAttribute("blog");
    User user = (User) session.getAttribute("user");

    if (blog == null) {
        response.sendRedirect("home");
        return;
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title><%= blog.getTitle() %> - 我的个人博客</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">我的技术博客</a>
        <div class="navbar-nav">
            <a class="nav-link" href="home">首页</a>
            <% if (user != null) { %>
            <span class="nav-link text-light">欢迎，<%= user.getUsername() %></span>
            <a class="nav-link" href="logout">退出</a>
            <% } else { %>
            <a class="nav-link" href="login.jsp">登录</a>
            <a class="nav-link" href="register.jsp">注册</a>
            <% } %>
        </div>
    </div>
</nav>

<main class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <!-- 博客详情 -->
            <article>
                <header class="mb-4">
                    <h1 class="display-4"><%= blog.getTitle() %></h1>
                    <div class="text-muted mb-3">
                        <span>作者：<%= blog.getAuthorName() %></span>
                        <span class="ms-3">发布时间：<%= blog.getCreatedTime() != null ? blog.getCreatedTime().toLocalDate().toString() : "未知时间" %></span>
                    </div>
                </header>

                <div class="blog-content">
                    <p style="white-space: pre-wrap; line-height: 1.8; font-size: 1.1rem;">
                        <%= blog.getContent() %>
                    </p>
                </div>

                <footer class="mt-5 pt-4 border-top">
                    <a href="home" class="btn btn-secondary">返回首页</a>
                </footer>
            </article>
        </div>
    </div>
</main>

<footer class="bg-dark text-white text-center py-3 mt-5">
    <div class="container">
        <p>&copy; 2025 我的个人博客 所有权利保留</p>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>