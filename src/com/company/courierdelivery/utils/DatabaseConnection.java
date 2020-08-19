package com.company.courierdelivery.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Database Connection class to retrieve database connection for the DAO
public class DatabaseConnection {
    public static Connection conn = null;

    public static Connection getConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (conn == null) {
            //System.out.println("Connecting to database...");
            try {
                conn = DriverManager.getConnection(Constants.DB_URL, Constants.DB_USER, Constants.DB_PASS);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}
