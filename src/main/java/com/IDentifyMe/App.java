package com.IDentifyMe;

import com.IDentifyMe.classes.Router;
import io.undertow.Undertow;
import org.mindrot.jbcrypt.BCrypt;



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
    public static String hashPassword(String password) {
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(password, salt);

        return hashedPassword;
    }
    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
