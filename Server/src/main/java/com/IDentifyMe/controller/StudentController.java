package com.IDentifyMe.controller;


import java.io.IOException;
import java.nio.ByteBuffer;
import org.json.JSONObject;
import com.IDentifyMe.App;
import com.IDentifyMe.classes.Router;
import com.IDentifyMe.classes.Serializer;
import com.IDentifyMe.database.StudentsTable;
import com.IDentifyMe.models.Student;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.Session;
import io.undertow.server.session.SessionManager;
import io.undertow.util.Headers;

public class StudentController {

    public static void login(HttpServerExchange exchange) {
        if (exchange.getAttachment(SessionManager.ATTACHMENT_KEY) == null) {
            exchange.getRequestReceiver().receiveFullBytes((exc, message) -> {
                try {
                    Student stud = Serializer.deserialize(message);
                    if (stud == null || stud.getStudentID() == null || stud.getPassword() == null) {
                        throw new IllegalArgumentException("Invalid Student object");
                    }
                    StudentsTable studT = new StudentsTable();
                    Student studC = studT.getStudent(stud);
                    if (studC != null && App.verifyPassword(stud.getPassword(), studC.getPassword())) {
                        Session newSession = Router.getSessionManager().createSession(exc, null);
                        newSession.setAttribute("user", studC);
                        sendResponse(exchange, 200, "successful", "Login successful");
                    } else {
                        sendResponse(exchange, 401, "falid", "Invalid username or password");
                    }
                } catch (IllegalArgumentException e) {
                    sendResponse(exchange, 400, "falid", "Invalid request");
                } catch (Exception e) {
                    sendResponse(exchange, 500, "falid", "Server error");
                }
            });
        } else {
            sendResponse(exchange, 200, "successful", "already logged in");
        }
    }

    public static void update(HttpServerExchange exchange) {
        if (exchange.getAttachment(SessionManager.ATTACHMENT_KEY) != null) {
            exchange.getRequestReceiver().receiveFullBytes((exc, message) -> {
                try {
                    Student stud = Serializer.deserialize(message);
                    if (stud == null || !stud.validateAttributes()) {
                        throw new IllegalArgumentException("Invalid Student object");
                    }
                    StudentsTable studT = new StudentsTable();
                    stud.hashPassword();
                    Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
                    if (studT.updateStudent(stud, ((Student) session.getAttribute("user")).getStudentID())) {
                        sendResponse(exchange, 200, "successful", "Update successful");
                    } else {
                        sendResponse(exchange, 500, "failed", "Server error");
                    }
                } catch (IllegalArgumentException e) {
                    sendResponse(exchange, 400, "falid", "Invalid request");
                } catch (Exception e) {
                    sendResponse(exchange, 500, "falid", "Server error");
                }
            });
        } else {
            sendResponse(exchange, 401, "falid", "login");
        }
    }

    public static void logout(HttpServerExchange exchange) {
        if (exchange.getAttachment(SessionManager.ATTACHMENT_KEY) != null) {
            Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
            session.invalidate(exchange);
            sendResponse(exchange, 200, "successful", "Logout successful");
        } else {
            sendResponse(exchange, 401, "falid", "No active session to logout");
        }
    }

    public static void getInfo(HttpServerExchange exchange) {
            if (exchange.getAttachment(SessionManager.ATTACHMENT_KEY) != null) {
                Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
                Student student = (Student) session.getAttribute("user");
                if (student != null) {
                    try {
                        byte[] studentBytes = Serializer.serialize(student);
                        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/octet-stream");
                        exchange.getResponseSender().send(ByteBuffer.wrap(studentBytes));
                    } catch (IOException e) {
                        e.printStackTrace();
                        sendResponse(exchange, 500, "falid", "Server error");
                    } 
                } else {
                    sendResponse(exchange, 401, "falid", "No student information in session");
                }
            } else {
                sendResponse(exchange, 401, "falid", "login");
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
