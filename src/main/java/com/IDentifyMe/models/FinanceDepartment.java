package com.IDentifyMe.models;

public class FinanceDepartment {
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

    // Getters and Setters
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
}
