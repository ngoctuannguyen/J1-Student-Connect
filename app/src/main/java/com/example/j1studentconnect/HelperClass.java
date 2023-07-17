package com.example.j1studentconnect;

public class HelperClass {
    String student_id, password, name, gender, student_class, email, birthday;

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStudent_class() {
        return student_class;
    }

    public void setStudent_class(String student_class) {
        this.student_class = student_class;
    }

    public HelperClass(String student_id, String password, String name, String student_class, String gender, String email, String birthday) {
        this.student_id = student_id;
        this.password = password;
        this.name = name;
        this.student_class = student_class;
        this.gender = gender;
        this.email = email;
        this.birthday = birthday;
    }
    public HelperClass() {
    }
}