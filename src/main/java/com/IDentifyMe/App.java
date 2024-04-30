package com.IDentifyMe;

import com.IDentifyMe.classes.Router;
import io.undertow.Undertow;


public class App {
    private Router router;
    private Undertow server;

    private App() {
        this.router = new Router();
        this.server = Undertow.builder()
                .addHttpListener(8081, "localhost")
                .setHandler(router)
                .build();
    }

    private void start() {
        server.start();
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

}
