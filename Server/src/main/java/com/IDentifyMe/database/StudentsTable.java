package com.IDentifyMe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.models.Student;

public class StudentsTable extends DatabaseManager {
    private final String tableName = "Students";

    public StudentsTable() {
        super();
    }


    public Boolean createStudent(Student student) {
        String query = "INSERT INTO " + tableName + " (StudentID, Name, Email, Password) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getStudentID());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getPassword());

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return (rowsInserted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Student getStudent(Student studentID) {
        return this.getStudent(studentID.getStudentID());
    }

    public Student getStudent(String studentID) {
        String query = "SELECT * FROM " + tableName + " WHERE StudentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, studentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Student student = new Student(resultSet);
                statement.close();
                return student;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updateStudent(Student student) {
        return this.updateStudent(student, student.getStudentID());
    }

    public Boolean updateStudent(Student student, String studentID) {
        String query = "UPDATE " + tableName + " SET Name = ?, Email = ?, Password = ? WHERE StudentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPassword());
            statement.setString(4, studentID);

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return (rowsUpdated > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deleteStudent(Student student) {
        String query = "DELETE FROM " + tableName + " WHERE StudentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getStudentID());
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return (rowsDeleted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
