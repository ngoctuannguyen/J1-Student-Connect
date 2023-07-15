package com.example.j1studentconnect;

public class HelperClass {
    String student_id, password;

    public String getStudent_id() {
        return student_id;
    }
    public void setStudent_id(String username) {
        this.student_id = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public HelperClass(String student_id, String password) {
        this.student_id = student_id;
        this.password = password;
    }
    public HelperClass() {
    }
}