package com.IDentifyMe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.models.PaymentDetail;

public class PaymentDetailsTable extends DatabaseManager {
    private final String tableName = "PaymentDetails";

    public PaymentDetailsTable() {
        super();
    }

    public Boolean createPaymentDetails(PaymentDetail paymentDetails) {
        String query = "INSERT INTO " + tableName + " (PaymentID, RequestID, Amount, ReceiptNumber, PaymentDate, PaymentVerificationDate, BankName, Status, Method) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, paymentDetails.getPaymentID());
            statement.setInt(2, paymentDetails.getRequestID());
            statement.setDouble(3, paymentDetails.getAmount());
            statement.setString(4, paymentDetails.getReceiptNumber());
            statement.setString(5, paymentDetails.getPaymentDate());
            statement.setString(6, paymentDetails.getPaymentVerificationDate());
            statement.setString(7, paymentDetails.getBankName());


            int rowsInserted = statement.executeUpdate();
            statement.close();
            return (rowsInserted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PaymentDetail getPaymentDetails(PaymentDetail paymentDetails) {
        return this.getPaymentDetails(paymentDetails.getPaymentID());
    }

    public PaymentDetail getPaymentDetails(int PaymentID) {
        String query = "SELECT * FROM " + tableName + " WHERE PaymentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, PaymentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PaymentDetail paymentDetails = new PaymentDetail(resultSet);
                statement.close();
                return paymentDetails;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updatePaymentDetails(PaymentDetail paymentDetails, PaymentDetail paymentDetailsID) {
        return this.updatePaymentDetails(paymentDetails, paymentDetails.getPaymentID());
    }

    public Boolean updatePaymentDetails(PaymentDetail paymentDetails, int PaymentID) {
        String query = "UPDATE " + tableName + " SET RequestID = ?, Amount = ?, ReceiptNumber = ?, PaymentDate = ?, PaymentVerificationDate = ?, BankName = ?, Status = ?, Method = ? WHERE PaymentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, paymentDetails.getRequestID());
            statement.setDouble(2, paymentDetails.getAmount());
            statement.setString(3, paymentDetails.getReceiptNumber());
            statement.setString(4, paymentDetails.getPaymentDate());
            statement.setString(5, paymentDetails.getPaymentVerificationDate());
            statement.setString(6, paymentDetails.getBankName());
            statement.setString(7, paymentDetails.getStatus());
            statement.setInt(8, PaymentID);

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return (rowsUpdated > 0) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deletePaymentDetails(PaymentDetail paymentDetails) {
        return this.deletePaymentDetails(paymentDetails.getPaymentID());
    }

    public Boolean deletePaymentDetails(int PaymentID) {
        String query = "DELETE FROM " + tableName + " WHERE PaymentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, PaymentID);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return (rowsDeleted > 0) ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    
}
