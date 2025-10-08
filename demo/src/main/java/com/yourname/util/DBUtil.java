package com.yourname.util;

import java.sql.*;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/my_blog?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("找不到MySql数据库驱动！", e);
        }
    }

    public static Connection getConnection(){
        try{
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("数据库连接成功！");
            return conn;
        }catch(SQLException e){
            e.printStackTrace();
            throw new RuntimeException("获取数据库链接失败",e);
        }
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs){
        try{
            if(rs != null) rs.close();
            if(stmt != null) stmt.close();
            if(conn != null) conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void close(Connection conn, Statement stmt){
        close(conn, stmt, null);
    }
}