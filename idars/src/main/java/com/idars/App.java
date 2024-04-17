package com.idars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.idars.classes.Router;

import io.undertow.Undertow;


public class App {
    private Router router;
    private Undertow server;
    public static Connection db;


    private App() throws SQLException {
        this.router = new Router();
        this.server = Undertow.builder()
                .addHttpListener(8081, "localhost")
                .setHandler(router)
                .build();
        this.db  = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/movies_db", "root", "admin");
    }

    private void start() {
        server.start();
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }
}
