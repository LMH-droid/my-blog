<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.yourname.entity.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
    <title>写博客 - 我的个人博客</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="home">我的技术博客</a>
        <div class="navbar-nav">
            <a class="nav-link" href="home">首页</a>
            <span class="nav-link text-light">欢迎，<%= user.getUsername() %></span>
            <a class="nav-link" href="logout">退出</a>
        </div>
    </div>
</nav>

<main class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-10">
            <div class="card">
                <div class="card-header">
                    <h4 class="text-center">写博客</h4>
                </div>
                <div class="card-body">
                    <% if (request.getAttribute("error") != null) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= request.getAttribute("error") %>
                    </div>
                    <% } %>

                    <form action="writeBlog" method="post">
                        <div class="mb-3">
                            <label for="title" class="form-label">标题</label>
                            <input type="text" class="form-control" id="title" name="title" required
                                   placeholder="请输入博客标题" maxlength="100">
                        </div>
                        <div class="mb-3">
                            <label for="content" class="form-label">内容</label>
                            <textarea class="form-control" id="content" name="content" rows="15"
                                      placeholder="请输入博客内容" required></textarea>
                        </div>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                            <a href="home" class="btn btn-secondary me-md-2">取消</a>
                            <button type="submit" class="btn btn-primary">发布</button>
                        </div>
                    </form>
                </div>
            </div>
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