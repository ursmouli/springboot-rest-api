package com.app.restapi.dto;

public class PeriodDetailsDto {
    private String subjectName;
    private String teacherName;
    private String classroom;

    public String getSubjectName() {
        return subjectName;
    }

    public PeriodDetailsDto setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public PeriodDetailsDto setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public String getClassroom() {
        return classroom;
    }

    public PeriodDetailsDto setClassroom(String classroom) {
        this.classroom = classroom;
        return this;
    }
}
