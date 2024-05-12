package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class Database {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_database";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "PASSWORD";

    private static HikariDataSource dataSource = null;

    private Database() {}

    public static void createConnection() {
        if(dataSource == null)
        {
            HikariConfig config = new HikariConfig();
            config.setLeakDetectionThreshold(500);
            config.setJdbcUrl(JDBC_URL);
            config.setUsername(USERNAME);
            config.setPassword(PASSWORD);

            dataSource = new HikariDataSource(config);
        }
    }


    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public static void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
