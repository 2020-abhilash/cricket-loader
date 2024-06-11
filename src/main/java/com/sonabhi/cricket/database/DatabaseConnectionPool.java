package com.sonabhi.cricket.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnectionPool {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static final HikariConfig hikariConfig = new HikariConfig();
    private static final HikariDataSource hikariDataSource;

    static {
        hikariConfig.setJdbcUrl(resourceBundle.getString("jdbcUrl"));
        hikariConfig.setUsername(resourceBundle.getString("username"));
        hikariConfig.setPassword(resourceBundle.getString("password"));
        hikariConfig.setMaximumPoolSize(Integer.parseInt(resourceBundle.getString("maximumPoolSize")));
        hikariConfig.setConnectionTimeout(Integer.parseInt(resourceBundle.getString("connectionTimeout")));
        hikariConfig.setLeakDetectionThreshold(Integer.parseInt(resourceBundle.getString("leakDetectionThreshold")));
        hikariDataSource = new HikariDataSource(hikariConfig);
    }

    private DatabaseConnectionPool() {}

    public static Connection getConnection() throws SQLException {
        return hikariDataSource.getConnection();
    }
}
