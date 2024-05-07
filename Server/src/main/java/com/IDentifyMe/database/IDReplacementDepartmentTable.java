package com.IDentifyMe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.models.IDReplacementDepartment;

public class IDReplacementDepartmentTable extends DatabaseManager {
    private final String tableName = "IDReplacementDepartment";

    public IDReplacementDepartmentTable() {
        super();
    }

    public Boolean createIDReplacementDepartment(IDReplacementDepartment IDReplacementDepartment) {
        String query = "INSERT INTO " + tableName + " (Name, Email, Password) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, IDReplacementDepartment.getName());
            statement.setString(2, IDReplacementDepartment.getEmail());
            statement.setString(3, IDReplacementDepartment.getPassword());

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return (rowsInserted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public IDReplacementDepartment getIDReplacementDepartment(IDReplacementDepartment IDReplacementDepartment) {
        return this.getIDReplacementDepartment(IDReplacementDepartment.getEmployeeID());
    }

    public IDReplacementDepartment getIDReplacementDepartment(int EmployeeID) {
        String query = "SELECT * FROM " + tableName + " WHERE EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, EmployeeID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                IDReplacementDepartment IDReplacementDepartment = new IDReplacementDepartment(resultSet);
                statement.close();
                return IDReplacementDepartment;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }   

    public Boolean updateIDReplacementDepartment(IDReplacementDepartment IDReplacementDepartment, IDReplacementDepartment IDReplacementDepartmentID) {
        return this.updateIDReplacementDepartment(IDReplacementDepartment, IDReplacementDepartment.getEmployeeID());
    }

    public Boolean updateIDReplacementDepartment(IDReplacementDepartment IDReplacementDepartment, int EmployeeID) {
        String query = "UPDATE " + tableName + " SET Name = ?, Email = ?, Password = ? WHERE EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, IDReplacementDepartment.getName());
            statement.setString(2, IDReplacementDepartment.getEmail());
            statement.setString(3, IDReplacementDepartment.getPassword());
            statement.setInt(4, EmployeeID);

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return (rowsUpdated > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deleteIDReplacementDepartment(IDReplacementDepartment IDReplacementDepartment) {
        return this.deleteIDReplacementDepartment(IDReplacementDepartment.getEmployeeID());
    }

    public Boolean deleteIDReplacementDepartment(int EmployeeID) {
        String query = "DELETE FROM " + tableName + " WHERE EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, EmployeeID);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return (rowsDeleted > 0) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
}
