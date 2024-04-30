package com.IDentifyMe.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

import org.json.JSONObject;

import com.IDentifyMe.classes.Router;
import com.IDentifyMe.database.StudentsTable;
import com.IDentifyMe.models.Student;

import io.undertow.server.HttpServerExchange;
import io.undertow.server.session.Session;
import io.undertow.server.session.SessionManager;
import io.undertow.util.Headers;

public class StudentController {

    public static void login(HttpServerExchange exchange) {
        Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
        if (session == null) {
            exchange.getRequestReceiver().receiveFullBytes((exc, message) -> {
                try {
                    ByteArrayInputStream bais = new ByteArrayInputStream(message);
                    ObjectInputStream obj = new ObjectInputStream(bais);
                    Student stud = (Student) obj.readObject();
                    if (stud == null || stud.getStudentID() == null || stud.getPassword() == null) {
                        throw new IllegalArgumentException("Invalid Student object");
                    }
                    StudentsTable studT = new StudentsTable();
                    Student studC = studT.getStudent(stud);
                    if (studC != null && stud.getPassword().equals(studC.getPassword())) {
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
        try {
            Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
            if (session != null) {
                exchange.getRequestReceiver().receiveFullBytes((exc, message) -> {
                    try {
                        ByteArrayInputStream bais = new ByteArrayInputStream(message);
                        ObjectInputStream obj = new ObjectInputStream(bais);
                        Student stud = (Student) obj.readObject();
                        if (stud == null || !stud.validateAttributes()) {
                            throw new IllegalArgumentException("Invalid Student object");
                        }
                        StudentsTable studT = new StudentsTable();
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

        } catch (Exception e) {
            sendResponse(exchange, 500, "falid", "Server error");
        }
    }

    public static void logout(HttpServerExchange exchange) {
        try {
            Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
            if (session != null) {
                session.invalidate(exchange);
                sendResponse(exchange, 200, "successful", "Logout successful");
            } else {
                sendResponse(exchange, 401, "falid", "No active session to logout");
            }
        } catch (Exception e) {
            sendResponse(exchange, 500, "falid", "Server error");
        }
    }

    public static void getInfo(HttpServerExchange exchange) {
        try {
            Session session = exchange.getAttachment(SessionManager.ATTACHMENT_KEY).getSession(exchange, null);
            if (session != null) {
                Student student = (Student) session.getAttribute("user");
                if (student != null) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(student);
                    oos.close();
                    byte[] studentBytes = baos.toByteArray();
                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/octet-stream");
                    exchange.getResponseSender().send(ByteBuffer.wrap(studentBytes));
                } else {
                    sendResponse(exchange, 401, "falid", "No student information in session");
                }
            } else {
                sendResponse(exchange, 401, "falid", "login");
            }
        } catch (Exception e) {
            sendResponse(exchange, 500, "falid", "Server error");
        }
    }

    private static void sendResponse(HttpServerExchange exchange, int statusCode, String status,  String message) {
        JSONObject jsonResponse = new JSONObject();
        jsonResponse.put("status", status);
        jsonResponse.put("message", message);

        exchange.setStatusCode(statusCode);
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(jsonResponse.toString());
    }

}
