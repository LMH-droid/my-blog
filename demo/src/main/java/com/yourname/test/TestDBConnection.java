package com.yourname.test;

import com.yourname.dao.BlogDao;
import com.yourname.entity.Blog;
import java.util.List;

public class TestDBConnection {
    public static void main(String[] args) {
        System.out.println("=== 测试数据库连接 ===");

        BlogDao blogDao = new BlogDao();
        System.out.println("创建BlogDao完成");

        List<Blog> blogs = blogDao.getAllBlogs();
        System.out.println("查询完成，结果数量: " + blogs.size());

        if (blogs.isEmpty()) {
            System.out.println("没有查询到博客数据！");
            // 检查数据库连接和查询逻辑
        } else {
            System.out.println("成功查询到 " + blogs.size() + " 篇博客：");
            for (Blog blog : blogs) {
                System.out.println("ID: " + blog.getId());
                System.out.println("标题: " + blog.getTitle());
                System.out.println("作者: " + blog.getAuthorName());
                System.out.println("创建时间: " + blog.getCreatedTime());
                System.out.println("------------------------");
            }
        }

        System.out.println("=== 测试结束 ===");
    }
}