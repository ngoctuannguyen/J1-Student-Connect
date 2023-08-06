package com.example.j1studentconnect.request;

public class RequestData {
    private String userName;
    private String state;
    private String currentDate;
    private String edtReason;

    public RequestData() {
        // Default constructor required for Firebase
    }

    public RequestData(String userName, String state, String currentDate, String edtReason) {
        this.userName = userName;
        this.state = state;
        this.currentDate = currentDate;
        this.edtReason = edtReason;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getEdtReason() {
        return edtReason;
    }

    public void setEdtReason(String edtReason) {
        this.edtReason = edtReason;
    }

// Getter and setter methods for each field
}
