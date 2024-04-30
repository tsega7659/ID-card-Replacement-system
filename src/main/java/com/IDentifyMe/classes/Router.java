package com.IDentifyMe.classes;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.server.session.InMemorySessionManager;
import io.undertow.server.session.SessionManager;
import io.undertow.util.Headers;
import com.IDentifyMe.controller.*;

public class Router extends RoutingHandler{
    private static final SessionManager sessionManager = new InMemorySessionManager("SESSION_MANAGER");
    
    public Router() {
        this.get("/", constantStringHandler("<h1>Homepage</h1>"));
        this.post("/student/login", StudentController::login);
        this.get("/student/logout", StudentController::logout);
        this.get("/student/getInfo", StudentController::getInfo);
        this.post("/student/update", StudentController::update);
    }

    public static HttpHandler constantStringHandler(String value) {
        return (HttpServerExchange exchange) -> {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE,   "text/html");
            exchange.getResponseSender().send(value);
        };
    } 

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        super.handleRequest(exchange);
    }

    public static SessionManager getSessionManager() {
        return sessionManager;
    }
    
}

