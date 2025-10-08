<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.yourname.entity.Blog" %>
<%@ page import="com.yourname.entity.User" %>
<%
    if (request.getAttribute("blogs") == null) {
        RequestDispatcher dispatcher = (RequestDispatcher) request.getRequestDispatcher("/home");
        dispatcher.forward((ServletRequest) request, (ServletResponse) response);
        return;
    }

    List<Blog> blogs = (List<Blog>) request.getAttribute("blogs");
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>我的个人博客</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">我的技术博客</a>
        <div class="navbar-nav">
            <a class="nav-link active" href="home">首页</a>
            <% if (user != null) { %>
            <a class="nav-link" href="writeBlog">写博客</a>
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
    <div class="p-5 mb-4 bg-light rounded-3">
        <h1 class="display-4">欢迎来到我的博客</h1>
        <p class="lead">这里是记录分析学习心得的区域</p>
    </div>

    <div class="row">
        <%
            if (blogs != null && !blogs.isEmpty()) {
                for (Blog blog : blogs) {
                    String content = blog.getContent();
                    String summary = content.length() > 100 ? content.substring(0, 100) + "..." : content;
        %>
        <div class="col-md-6 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title"><%= blog.getTitle() %></h5>
                    <p class="card-text"><%= summary %></p>
                    <a href="blogDetail?id=<%= blog.getId() %>" class="btn btn-primary">阅读更多</a>
                </div>
                <div class="card-footer text-muted">
                    作者：<%= blog.getAuthorName() %> |
                    发布时间：<%= blog.getCreatedTime() != null ? blog.getCreatedTime().toLocalDate().toString() : "未知时间" %>
                </div>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <div class="col-12 text-center">
            <div class="alert alert-info" role="alert">
                暂无博客文章
            </div>
        </div>
        <%
            }
        %>
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