package com.IDentifyMe.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;


public class Student {
    private String StudentID ;
    private String Name;
    private String Email;
    private String Password;

    public Student(String StudentID, String Name, String Email, String Password) {
        this.StudentID = StudentID;
        this.Name = Name;
        this.Email = Email;
        this.Password = Password;
    }

    public Student(ResultSet rs) throws SQLException {
        this.StudentID = rs.getString("StudentID");
        this.Name = rs.getString("Name");
        this.Email = rs.getString("Email");
        this.Password = rs .getString("Password");
    }

        public Student(JSONObject json)  {
        this(
            json.getString("StudentID"),
            json.getString("Name"),
            json.getString("Email"),
            json.getString("Password")
        );
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("StudentID", this.StudentID);
        json.put("Name", this.Name);
        json.put("Email", this.Email);
        json.put("Password", this.Password);
        return json;
    }

    public String getStudentID() {
        return this.StudentID;
    }
    public String getName() {
        return this.Name;
    }
    public String getEmail() {
        return this.Email;
    }
    public String getPassword() {
        return this.Password;
    }
    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }
    public void setName(String Name) {
        this.Name = Name;
    }
    public void setEmail(String Email) {
        this.Email = Email;
    }
    public void setPassword(String Password) {
        this.Password = Password;
    }
    public boolean validateAttributes() {
        if (StudentID == null || StudentID.isEmpty() || !StudentID.matches("[a-zA-Z]{3}[0-9]{4}/[0-9]{2}")) {
            return false;
        }
        if (Name == null || Name.isEmpty()) {
            return false;
        }
        if (Email == null || Email.isEmpty() || !Email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            return false;
        }
        if (Password == null || Password.isEmpty() || Password.length() < 8) {
            return false;
        }
        return true;
    }
}
