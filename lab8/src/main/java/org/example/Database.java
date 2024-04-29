package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";

    private static final String USER = "STUDENT";
    private static final String PASSWORD = "STUDENT";
    private static Connection connection = null;

    private Database() {}
    public static Connection getConnection() {
        createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            // create connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
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
