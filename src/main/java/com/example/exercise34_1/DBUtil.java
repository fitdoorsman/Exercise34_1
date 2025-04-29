package com.example.exercise34_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/Exercise34_1";
    private static final String JDBC_USER = "root"; // change if using a different user
    private static final String JDBC_PASSWORD = "Ibelieve1437!"; // change to match your actual password

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }
}