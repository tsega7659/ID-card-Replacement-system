package com.idars.classes;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

public class Router implements HttpHandler{
    
    @Override
    public void handleRequest(HttpServerExchange httpServerExchange) throws Exception {
        httpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
        httpServerExchange
                .getResponseSender()
                .send("<!DOCTYPE html<>html<>body<>h1<Hello World from Undertow>/h1<>/body<>/html>");
    }
    
}
