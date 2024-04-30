package com.IDentifyMe.classes;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;

public class Router extends RoutingHandler{
    
    public Router() {
        this.get("/", constantStringHandler("<h1>GET - My Homepage<h1>"));
        this.get("/myRoute", constantStringHandler("GET - My Route"));
        this.post("/myRoute", constantStringHandler("POST - My Route"));
        this.get("/myOtherRoute", constantStringHandler("GET - My Other Route"));
        this.get("/myRoutePrefix*", constantStringHandler("GET - My Prefixed Route"));
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
        System.out.println(exchange.getRequestPath());
    }
    
}

