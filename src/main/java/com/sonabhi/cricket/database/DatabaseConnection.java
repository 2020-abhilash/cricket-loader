package com.sonabhi.cricket.database;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DatabaseConnection {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
    private static final String URL = resourceBundle.getString("jdbcUrl");
    private static final String USER = resourceBundle.getString("username");
    private static final String PASSWORD = resourceBundle.getString("password");

    private static final Logger logger = LogManager.getLogger(DatabaseConnection.class);

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Error while connecting to the database", e);
        }
        return connection;
    }
}
