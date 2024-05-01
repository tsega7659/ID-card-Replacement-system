package com.IDentifyMe.models;

import java.io.Serializable;

public class FinanceDepartment implements Serializable{
    private int employeeID;
    private String name;
    private String email;
    private String password;

    // Constructor
    public FinanceDepartment(int employeeID, String name, String email, String password) {
        this.employeeID = employeeID;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean validateAttributes() {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (email == null || email.isEmpty() || !email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")) {
            return false;
        }
        if (password == null || password.isEmpty() || password.length() < 8) {
            return false;
        }
        return true;
    }
}
