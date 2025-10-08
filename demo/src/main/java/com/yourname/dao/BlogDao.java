package com.yourname.dao;

import com.yourname.entity.Blog;
import com.yourname.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDao {
    public List<Blog> getAllBlogs(){
        List<Blog> blogList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT b.*, u.username as author_name FROM blogs b " +
                    "JOIN users u ON b.user_id = u.id " +
                    "ORDER BY b.created_time DESC";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Blog blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setUserId(rs.getInt("user_id"));
                blog.setAuthorName(rs.getString("author_name"));
                blog.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());

                blogList.add(blog);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally{
            DBUtil.close(conn,stmt,rs);
        }
        return blogList;
    }

    public Blog getBlogById(int blogId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Blog blog = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT b.*, u.username as author_name FROM blogs b " +
                    "JOIN users u ON b.user_id = u.id " +
                    "WHERE b.id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, blogId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                blog = new Blog();
                blog.setId(rs.getInt("id"));
                blog.setTitle(rs.getString("title"));
                blog.setContent(rs.getString("content"));
                blog.setUserId(rs.getInt("user_id"));
                blog.setAuthorName(rs.getString("author_name"));
                blog.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return blog;
    }

    public boolean addBlog(Blog blog) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO blogs(title, content, user_id, created_time, updated_time) VALUES(?, ?, ?, NOW(), NOW())";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, blog.getTitle());
            stmt.setString(2, blog.getContent());
            stmt.setInt(3, blog.getUserId());

            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt);
        }
        return false;
    }
}