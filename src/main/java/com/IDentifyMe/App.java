package com.IDentifyMe;

import com.IDentifyMe.classes.Router;
import io.undertow.Undertow;
import io.undertow.server.session.InMemorySessionManager;
import io.undertow.server.session.SessionManager;


public class App {
    private Router router;
    private Undertow server;
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    private App() {
        this.router = new Router();
        this.server = Undertow.builder()
                .addHttpListener( PORT, HOST)
                .setHandler(router)
                .build();
    }

    private void start() {
        server.start();
    }

    public static void main(String[] args) {
        App app = new App();
        app.start();
        System.out.println("Server running : http://" + HOST + ":" + PORT);
    }

}
