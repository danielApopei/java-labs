package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:mysql://localhost:3306/test_database";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static Connection connection = null;

    private Database() {}

    public static Connection getConnection() {
        return createConnection();
    }

    private static Connection createConnection() {
        try {
            // create connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;  // Help garbage collector by nullifying the reference
            } catch (SQLException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    public static void rollback() {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                System.err.println("Could not rollback transaction: " + e.getMessage());
            }
        }
    }
}