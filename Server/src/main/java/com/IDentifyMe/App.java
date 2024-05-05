package com.IDentifyMe;

import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.classes.Router;
import io.undertow.Undertow;
import io.undertow.server.session.InMemorySessionManager;
import io.undertow.server.session.SessionAttachmentHandler;
import io.undertow.server.session.SessionCookieConfig;
import io.undertow.server.session.SessionManager;

public class App {
    private Router router;
    private Undertow server;
    public final static int PORT = 8081;
    public final static String HOST = "localhost";

    private App() {
        SessionManager sessionManager = new InMemorySessionManager("IDentifyMe");
        SessionCookieConfig sessionCookieConfig=new SessionCookieConfig();
        sessionCookieConfig.setCookieName("SESSION_ID");
        
        this.router = new Router();
        SessionAttachmentHandler sessionAttachmentHandler = new SessionAttachmentHandler(router, sessionManager, sessionCookieConfig);
        this.server = Undertow.builder()
                .addHttpListener(PORT, HOST)
                .setHandler(sessionAttachmentHandler)
                .build();
    }

    private void start() {
        server.start();
    }

    public static void main(String[] args) {
        DatabaseManager.testDatabaseConnection();
        App app = new App();
        app.start();
        System.out.println("Server running : http://" + HOST + ":" + PORT);
    }
}
