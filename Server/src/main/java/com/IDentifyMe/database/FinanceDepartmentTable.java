package com.IDentifyMe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.models.FinanceDepartment;

public class FinanceDepartmentTable extends DatabaseManager {
    private final String tableName = "FinanceDepartment";

    public FinanceDepartmentTable() {
        super();
    }

    public Boolean createFinanceDepartment(FinanceDepartment financeDepartment) {
        String query = "INSERT INTO " + tableName + " (Name, Email, Password) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, financeDepartment.getName());
            statement.setString(2, financeDepartment.getEmail());
            statement.setString(3, financeDepartment.getPassword());

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return (rowsInserted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public FinanceDepartment getFinanceDepartment(FinanceDepartment financeDepartment) {
        return this.getFinanceDepartment(financeDepartment.getEmployeeID());
    }

    public FinanceDepartment getFinanceDepartment(int EmployeeID) {
        String query = "SELECT * FROM " + tableName + " WHERE EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, EmployeeID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                FinanceDepartment financeDepartment = new FinanceDepartment(resultSet);
                statement.close();
                return financeDepartment;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updateFinanceDepartment(FinanceDepartment financeDepartment, FinanceDepartment financeDepartmentID) {
        return this.updateFinanceDepartment(financeDepartment, financeDepartment.getEmployeeID());
    }

    public Boolean updateFinanceDepartment(FinanceDepartment financeDepartment, int EmployeeID) {
        String query = "UPDATE " + tableName + " SET Name = ?, Email = ?, Password = ? WHERE EmployeeID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, financeDepartment.getName());
            statement.setString(2, financeDepartment.getEmail());
            statement.setString(3, financeDepartment.getPassword());
            statement.setInt(4, EmployeeID);

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return (rowsUpdated > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deleteFinanceDepartment(FinanceDepartment financeDepartment) {
        return this.deleteFinanceDepartment(financeDepartment.getEmployeeID());
    }

    public Boolean deleteFinanceDepartment(int EmployeeID) {
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
