package com.example.j1studentconnect;

public class StudentForSearch {

    private String StudentInfoSearch;
    private String ClassIdSearch;
    private String ClassNameSearch;
    private String PortalNumberSearch;

    public StudentForSearch(String studentInfoSearch, String classIdSearch, String classNameSearch, String portalNumberSearch) {
        this.StudentInfoSearch = studentInfoSearch;
        this.ClassIdSearch = classIdSearch;
        this.ClassNameSearch = classNameSearch;
        this.PortalNumberSearch = portalNumberSearch;
    }

    public String getStudentInfoSearch() {
        return StudentInfoSearch;
    }

    public void setStudentInfoSearch(String studentInfoSearch) {
        this.StudentInfoSearch = studentInfoSearch;
    }

    public String getClassIdSearch() {
        return ClassIdSearch;
    }

    public void setClassIdSearch(String classIdSearch) {
        this.ClassIdSearch = classIdSearch;
    }

    public String getClassNameSearch() {
        return ClassNameSearch;
    }

    public void setClassNameSearch(String classNameSearch) {
        this.ClassNameSearch = classNameSearch;
    }

    public String getPortalNumberSearch() {
        return PortalNumberSearch;
    }

    public void setPortalNumberSearch(String portalNumberSearch) {
        this.PortalNumberSearch = portalNumberSearch;
    }
}
