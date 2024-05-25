package com.IDentifyMe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.models.Request;


public class RequestsTable extends DatabaseManager{ 
    private final String tableName = "Requests";

    public RequestsTable() {
        super();
    }

    public Boolean createRequest(Request request) {
        String query = "INSERT INTO " + tableName + " (RequestID, StudentID, RequestType, Status, RequestDate, AppointmentDate, Reason) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, request.getRequestId());
            statement.setInt(2, request.getStudentId());
            statement.setString(3, request.getRequestType());
            statement.setString(4, request.getStatus());
            statement.setString(5, request.getRequestDate().toString());
            statement.setString(6, request.getReason());

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return (rowsInserted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Request getRequest(Request request) {
        return this.getRequest(request.getRequestId());
    }

    public Request getRequest(int RequestID) {
        String query = "SELECT * FROM " + tableName + " WHERE RequestID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, RequestID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Request request = new Request(resultSet);
                statement.close();
                return request;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();  
        }
        return null;
    }

    public Boolean updateRequest(Request request, Request requestId) {
        return this.updateRequest(request, request.getRequestId());
    }

    public Boolean updateRequest(Request request, int RequestID) {
        String query = "UPDATE " + tableName + " SET StudentID = ?, RequestType = ?, Status = ?, RequestDate = ?, AppointmentDate = ?, Reason = ? WHERE RequestID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, request.getStudentId());
            statement.setString(2, request.getRequestType());
            statement.setString(3, request.getStatus());
            statement.setString(4, request.getRequestDate().toString());
            statement.setString(5, request.getAppointmentDate().toString());
            statement.setString(6, request.getReason());
            statement.setInt(7, RequestID);

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return (rowsUpdated > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deleteRequest(Request request) {
        return this.deleteRequest(request.getRequestId());
    }

    public Boolean deleteRequest(int RequestID) {
        String query = "DELETE FROM " + tableName + " WHERE RequestID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, RequestID);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return (rowsDeleted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
}
