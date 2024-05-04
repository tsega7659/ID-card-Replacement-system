package org.IDentifyMe.Models;

import org.json.JSONObject;

public class Document {
    private int documentID;
    private int requestID;
    private String documentType;
    private String documentPath;

    public Document(int documentID, int requestID, String documentType, String documentPath) {
        this.documentID = documentID;
        this.requestID = requestID;
        this.documentType = documentType;
        this.documentPath = documentPath;
    }
    public Document(JSONObject json ){
        this(
            json.getInt("documentID"),
            json.getInt("requestID"),
            json.getString("documentType"),
            json.getString("documentPath")
        );
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("documentID", this.documentID);
        json.put("requestID", this.requestID);
        json.put("documentType", this.documentType);
        json.put("documentPath", this.documentPath);
        return json;
    }
    public int getDocumentID() {
        return documentID;
    }

    public void setDocumentID(int documentID) {
        this.documentID = documentID;
    }

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
}
