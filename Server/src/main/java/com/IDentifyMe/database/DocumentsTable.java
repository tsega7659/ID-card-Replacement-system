package com.IDentifyMe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.IDentifyMe.classes.DatabaseManager;
import com.IDentifyMe.models.Document;

public class DocumentsTable extends  DatabaseManager{ 
    private final String tableName = "Documents";

    public DocumentsTable() {
        super();
    }

    public Boolean createDocument(Document document) {
        String query = "INSERT INTO " + tableName + " (DocumentID, RequestID, DocumentType, DocumentPath) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, document.getDocumentID());
            statement.setInt(2, document.getRequestID());
            statement.setString(3, document.getDocumentType());
            statement.setString(4, document.getDocumentPath());

            int rowsInserted = statement.executeUpdate();
            statement.close();
            return (rowsInserted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Document getDocument(Document document) {
        return this.getDocument(document.getDocumentID());
    }

    public Document getDocument(int DocumentID) {
        String query = "SELECT * FROM " + tableName + " WHERE DocumentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, DocumentID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Document document = new Document(resultSet);
                statement.close();
                return document;
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Boolean updateDocument(Document document, Document documentID) {
        return this.updateDocument(document, document.getDocumentID());
    }

    public Boolean updateDocument(Document document, int DocumentID) {
        String query = "UPDATE " + tableName + " SET RequestID = ?, DocumentType = ?, DocumentPath = ? WHERE DocumentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, document.getRequestID());
            statement.setString(2, document.getDocumentType());
            statement.setString(3, document.getDocumentPath());
            statement.setInt(4, DocumentID);

            int rowsUpdated = statement.executeUpdate();
            statement.close();
            return (rowsUpdated > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean deleteDocument(Document document) {
        return this.deleteDocument(document.getDocumentID());
    }

    public Boolean deleteDocument(int DocumentID) {
        String query = "DELETE FROM " + tableName + " WHERE DocumentID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, DocumentID);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return (rowsDeleted > 0) ? true : false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    
    
}
