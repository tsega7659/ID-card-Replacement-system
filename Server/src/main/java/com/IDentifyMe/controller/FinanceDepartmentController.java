package com.IDentifyMe.controller;

import org.json.JSONException;
import org.json.JSONObject;
import com.IDentifyMe.database.FinanceDepartmentTable;
import com.IDentifyMe.models.FinanceDepartment;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.Session;
import io.undertow.server.session.SessionConfig;
import io.undertow.server.session.SessionManager;
import io.undertow.util.Headers;

public class FinanceDepartmentController {
     public static void login(HttpServerExchange exchange) {
        if (getSession(exchange) != null && getAttribute(getSession(exchange)) != null) {
            sendResponse(exchange, 200, "successful", "Already logged in");
            return;
        }

        exchange.getRequestReceiver().receiveFullString(((exc, message) -> {
            try {
                String jsonString = new String(message);
                FinanceDepartment fid = new FinanceDepartment(new JSONObject(jsonString));

                if (fid == null || fid.getPassword() == null) {
                    throw new IllegalArgumentException("FinanceDepartment object, student ID, or password is null");
                }
                
                FinanceDepartmentTable studT = new FinanceDepartmentTable();
                FinanceDepartment fidC = studT.getFinanceDepartment(fid);

                if (fidC != null && fidC.getPassword().equals(fid.getPassword())) {
                    Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).createSession(exchange, exchange.getAttachment(SessionConfig.ATTACHMENT_KEY));
                    session.setAttribute("user", fidC);
                    sendResponse(exchange, 200, "successful", "Login successful");
                } else {
                    sendResponse(exchange, 401, "failed", "Invalid username or password");
                }
            } catch (IllegalArgumentException e) {
                sendResponse(exchange, 400, "failed", e.getMessage());
            } catch (JSONException e) {
                sendResponse(exchange, 400, "failed", "Invalid JSON format");
            } catch (Exception e) {
                System.out.println(e.getMessage());
                sendResponse(exchange, 500, "failed", "Server error: " + e.getMessage());
            }
        }));
    }
    
    private static FinanceDepartment getAttribute(Session session){
        return (FinanceDepartment)session.getAttribute("user");
    }

    private static Session getSession (HttpServerExchange exchange){
        return exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, exchange.getAttachment(SessionConfig.ATTACHMENT_KEY));
    }

    public static void update(HttpServerExchange exchange) {
        if (getSession(exchange) == null || getAttribute(getSession(exchange)) == null) {
            sendResponse(exchange, 401, "falid", "login");
            return;
        }
        exchange.getRequestReceiver().receiveFullString((exc, message) -> {
            try {
                String jsonString = new String(message);
                FinanceDepartment fid = new FinanceDepartment(new JSONObject(jsonString));

                if (fid == null || !fid.validateAttributes()) {
                    throw new IllegalArgumentException("Invalid FinanceDepartment object");
                }
                FinanceDepartmentTable fidT = new FinanceDepartmentTable();
                if (fidT.updateFinanceDepartment(fid, getAttribute(getSession(exchange)))) {
                    getSession(exchange).setAttribute("user", fid);
                    sendResponse(exchange, 200, "successful", "Update successful");
                } else {
                    sendResponse(exchange, 500, "failed", "Server error");
                }
            } catch (IllegalArgumentException e) {
                sendResponse(exchange, 400, "failed", e.getMessage());
            } catch (JSONException e) {
                sendResponse(exchange, 400, "failed", "Invalid JSON format");
            } catch (Exception e) {
                sendResponse(exchange, 500, "failed", "Server error: " + e.getMessage());
            }
        });
    }

    public static void logout(HttpServerExchange exchange) {
        if (getSession(exchange) != null && getAttribute(getSession(exchange)) != null) {
            getSession(exchange).invalidate(exchange);
            sendResponse(exchange, 200, "successful", "Logout successful");
        } else {
            sendResponse(exchange, 401, "falid", "No active session to logout");
        }
    }

    public static void getInfo(HttpServerExchange exchange) {
        if ((getSession(exchange) == null || getAttribute(getSession(exchange)) == null)) {
            sendResponse(exchange, 401, "falid", "login");
            return;
        }
        FinanceDepartment student = getAttribute(getSession(exchange));
        if (student != null) {
            exchange.getResponseSender().send(student.toJSON().toString());
        } else {
            getSession(exchange).invalidate(exchange);
            sendResponse(exchange, 401, "falid", "No student information in session");
        }
    }

    private static void sendResponse(HttpServerExchange exchange, int statusCode, String status, String message) {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", status);
        jsonResponse.put("message", message);

        exchange.setStatusCode(statusCode);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(jsonResponse.toString());
    }

    
}
