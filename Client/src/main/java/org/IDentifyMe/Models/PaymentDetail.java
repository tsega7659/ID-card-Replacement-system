package org.IDentifyMe.Models;

import org.json.JSONObject;

public class PaymentDetail {
    private int paymentID;
    private int requestID;
    private double amount;
    private String receiptNumber;
    private String paymentDate;
    private String paymentMethod;
    private String status;
    private String paymentVerificationDate;
    private String bankName;

    public PaymentDetail(int paymentID, int requestID, double amount, String receiptNumber, String paymentDate, String status, String paymentVerificationDate, String bankName, String paymentMethod) {
        this.paymentID = paymentID;
        this.requestID = requestID;
        this.amount = amount;
        this.receiptNumber = receiptNumber;
        this.paymentDate = paymentDate;
        this.status = status;
        this.paymentVerificationDate = paymentVerificationDate;
        this.bankName = bankName;
        this.paymentMethod = paymentMethod;

    }
    public PaymentDetail() {
        this.paymentID = 0;
        this.requestID = 0;
        this.amount = 0;
        this.receiptNumber = "";
        this.paymentDate = "";
        this.status = "";
        this.paymentVerificationDate = "";
        this.bankName = "";
    }
    public PaymentDetail(JSONObject json){
        this(
            json.getInt("paymentID"),
            json.getInt("requestID"),
            json.getDouble("amount"),
            json.getString("receiptNumber"),
            json.getString("paymentDate"),
            json.getString("status"),
            json.getString("paymentVerificationDate"),
            json.getString("bankName"),
            json.getString("paymentMethod")
        );
    }
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("paymentID", this.paymentID);
        json.put("requestID", this.requestID);
        json.put("amount", this.amount);
        json.put("receiptNumber", this.receiptNumber);
        json.put("paymentDate", this.paymentDate);
        json.put("status", this.status);
        json.put("paymentVerificationDate", this.paymentVerificationDate);
        json.put("paymentMethod", this.paymentMethod);
        json.put("bankName", this.bankName);
        return json;
    }
    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String stat) {
        this.status=stat;
    }
    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentVerificationDate() {
        return paymentVerificationDate;
    }

    public void setPaymentVerificationDate(String paymentVerificationDate) {
        this.paymentVerificationDate = paymentVerificationDate;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
