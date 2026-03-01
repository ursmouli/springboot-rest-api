package com.app.restapi.dto;

import com.app.restapi.jpa.entity.SectionId;

public class TimetableEntryDto {
    private SectionId sectionId;
    private String sectionName;
    private String dayOfWeek;
    private Integer periodNumber; // 1, 2, 3...
    private String subjectName;
    private String teacherName;

    public SectionId getSectionId() {
        return sectionId;
    }

    public void setSectionId(SectionId sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public void setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
