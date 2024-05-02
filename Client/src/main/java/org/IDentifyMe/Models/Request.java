package org.IDentifyMe.Models;

import java.io.Serializable;
import java.util.Date;

import org.json.JSONObject;

public class Request implements Serializable{
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
    public Request(JSONObject json ) {
        this(
            json.getInt("requestId"),
            json.getInt("studentId"),
            json.getString("requestType"),
            new Date(json.getLong("requestDate")),
            json.getString("status"),
            new Date(json.getLong("appointmentDate"))
        );
    }
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("requestId", this.requestId);
        json.put("studentId", this.studentId);
        json.put("requestType", this.requestType);
        json.put("requestDate", this.requestDate.getTime());
        json.put("status", this.status);
        json.put("appointmentDate", this.appointmentDate.getTime());
        return json;
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
