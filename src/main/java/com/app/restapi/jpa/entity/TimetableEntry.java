package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.time.DayOfWeek;

@Entity
@Table(name = "timetable_entries", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_section_day_period",
                columnNames = {"school_class_id", "class_teacher_id", "dayOfWeek", "periodNumber"}
        )
})
public class TimetableEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "school_class_id", referencedColumnName = "school_class_id"),
            @JoinColumn(name = "class_teacher_id", referencedColumnName = "class_teacher_id")
    })
    private Section section;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Integer periodNumber; // 1, 2, 3...
    private String subjectName;
    private String teacherName;
    private String classroom;

    public Long getId() {
        return id;
    }

    public TimetableEntry setId(Long id) {
        this.id = id;
        return this;
    }

    public Section getSection() {
        return section;
    }

    public TimetableEntry setSection(Section section) {
        this.section = section;
        return this;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public TimetableEntry setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public Integer getPeriodNumber() {
        return periodNumber;
    }

    public TimetableEntry setPeriodNumber(Integer periodNumber) {
        this.periodNumber = periodNumber;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public TimetableEntry setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public TimetableEntry setTeacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public String getClassroom() {
        return classroom;
    }

    public TimetableEntry setClassroom(String classroom) {
        this.classroom = classroom;
        return this;
    }
}
