package com.IDentifyMe.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManager {
    private final String DATABASE_NAME = "IDentifyMe";
    private final String JDBC_URL = "jdbc:mysql://localhost:3307/" + this.DATABASE_NAME;
    private final String JDBC_USERNAME = "root";
    private final String JDBC_PASSWORD = "admin";
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";

    protected Connection connection;

    public DatabaseManager() {
        try {
            Class.forName(this.JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}