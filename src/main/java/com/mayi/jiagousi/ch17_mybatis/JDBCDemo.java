package com.mayi.jiagousi.ch17_mybatis;

import java.sql.*;

public class JDBCDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // sql注入
        String name = "' or 1 = '1";
        String sql = "select id,name from user where name = '" + name + "'";
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
        PreparedStatement stat = connection.prepareStatement(sql);
        System.out.println(stat.toString());
        ResultSet rs = stat.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            String n = rs.getString(2);
            System.out.println("id: " + id + ", name = " + n);
        }
    }
}
