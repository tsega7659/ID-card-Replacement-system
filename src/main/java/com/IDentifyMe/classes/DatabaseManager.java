package com.IDentifyMe.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseManager {
    private final String DATABASE_NAME = "mydatabase";
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/" + this.DATABASE_NAME;
    private final String JDBC_USERNAME = "username";
    private final String JDBC_PASSWORD = "password";
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String TABLE_NAME;


    protected Connection connection;

    public DatabaseManager() {
        try {
            Class.forName(this.JDBC_DRIVER);
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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

    public abstract void create();
    public abstract void read();
    public abstract void update();
    public abstract void delete();
}