package com.IDentifyMe.models;

import java.util.Date;

public class Request {
    private int requestId;
    private int studentId;
    private String requestType;
    private Date requestDate;
    private String status;
    private Date appointmentDate;

    public Request(int requestId, int studentId, String requestType, Date requestDate, String status, Date appointmentDate) {
        this.requestId = requestId;
        this.studentId = studentId;
        this.requestType = requestType;
        this.requestDate = requestDate;
        this.status = status;
        this.appointmentDate = appointmentDate;
    }
    public int getRequestId() {
        return this.requestId;
    }
    public int getStudentId() {
        return this.studentId;
    }
    public String getRequestType() {
        return this.requestType;
    }
    public Date getRequestDate() {
        return this.requestDate;
    }
    public String getStatus() {
        return this.status;
    }
    public Date getAppointmentDate() {
        return this.appointmentDate;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

}
