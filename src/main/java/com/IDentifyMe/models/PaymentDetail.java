package com.IDentifyMe.models;

public class PaymentDetail {
    private int paymentID;
    private int requestID;
    private double amount;
    private String receiptNumber;
    private String paymentDate;
    private String paymentVerificationDate;
    private String bankName;

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
}
