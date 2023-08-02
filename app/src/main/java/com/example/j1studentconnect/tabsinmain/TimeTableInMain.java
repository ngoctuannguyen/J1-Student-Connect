package com.example.j1studentconnect.tabsinmain;

import com.google.firebase.firestore.FirebaseFirestore;

public class TimeTableInMain {


    String lessonTime;
    String name_of_subject;
    String id_of_subject;
    String classroom;

    public TimeTableInMain(String lessonTime, String name_of_subject, String id_of_subject, String classroom) {
        this.lessonTime = lessonTime;
        this.name_of_subject = name_of_subject;
        this.id_of_subject = id_of_subject;
        this.classroom = classroom;
    }

    public String getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(String lessonTime) {
        this.lessonTime = lessonTime;
    }

    public String getName_of_subject() {
        return name_of_subject;
    }

    public void setName_of_subject(String name_of_subject) {
        this.name_of_subject = name_of_subject;
    }

    public String getId_of_subject() {
        return id_of_subject;
    }

    public void setId_of_subject(String id_of_subject) {
        this.id_of_subject = id_of_subject;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
