package com.IDentifyMe.classes;

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.resource.ClassPathResourceManager;
import io.undertow.server.session.InMemorySessionManager;
import io.undertow.server.session.SessionCookieConfig;
import io.undertow.server.session.SessionManager;
import io.undertow.util.Headers;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import com.IDentifyMe.App;
import com.IDentifyMe.controller.*;

public class Router extends RoutingHandler {

    public Router() {
        this.get("/", Handlers.resource(new ClassPathResourceManager(App.class.getClassLoader(), "webroot")));
        this.get("/images/{fileName}", this::serveDynamicImage);
        this.post("/student/login", StudentController::login);
        this.get("/student/logout", StudentController::logout);
        this.get("/student/getInfo", StudentController::getInfo);
        this.post("/student/update", StudentController::update);

        this.post("/finance/login", FinanceDepartmentController::login);
        this.get("/finance/logout", FinanceDepartmentController::logout);
        this.get("/finance/getInfo", FinanceDepartmentController::getInfo);
        this.post("/finance/update", FinanceDepartmentController::update);

        this.post("/ID_Department/login", IDReplacementDepartmentController::login);
        this.get("/ID_Department/logout", IDReplacementDepartmentController::logout);
        this.get("/ID_Department/getInfo", IDReplacementDepartmentController::getInfo);
        this.post("/ID_Department/update", IDReplacementDepartmentController::update);
    }

    private void serveDynamicImage(HttpServerExchange exchange) throws IOException {
        String requestPath = exchange.getRequestPath();
        String imageName = requestPath.substring("/images/".length());

        URL url = Router.class.getResource("/images/" + imageName);
        if (url != null && imageName.matches(".*\\.(png|jpg|jpeg|gif)")) {
            try (InputStream inputStream = url.openStream()) {
                byte[] imageBytes = inputStream.readAllBytes();
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "image/" + imageName.substring(imageName.lastIndexOf(".") + 1));    
                ByteBuffer buffer = ByteBuffer.wrap(imageBytes);
                exchange.getResponseSender().send(buffer);
            }catch (IOException e){
                exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
                exchange.setStatusCode(404);
                exchange.getResponseSender().send("Not Found");
            }
        } else {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
            exchange.setStatusCode(404);
            exchange.getResponseSender().send("Not Found");
        }
    }

    public static HttpHandler constantStringHandler(String value) {
        return (HttpServerExchange exchange) -> {
            exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/html");
            exchange.getResponseSender().send(value);
        };
    }

    @Override
    public void handleRequest(HttpServerExchange exchange) throws Exception {
        super.handleRequest(exchange);
    }
}
