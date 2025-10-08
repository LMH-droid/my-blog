package com.yourname.dao;

import com.yourname.entity.User;
import com.yourname.util.DBUtil;

import java.sql.*;

public class UserDao {

    public User findByUsername(String username) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "SELECT * FROM users WHERE username = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCreatedTime(rs.getTimestamp("created_time").toLocalDateTime());

                System.out.println("UserDao - 查询用户: " + username + ", ID: " + user.getId());
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, stmt, rs);
        }
        return null;
    }

    public boolean addUser(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DBUtil.getConnection();
            String sql = "INSERT INTO users(username, password, email, created_time) VALUES(?, ?, ?, NOW())";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());

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