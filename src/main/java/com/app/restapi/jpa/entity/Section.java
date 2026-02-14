package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "section")
//@IdClass(SectionId.class)
public class Section {

    @EmbeddedId
    private SectionId id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @MapsId("schoolClassId")
    @JoinColumn(name = "school_class_id")
    private SchoolClass schoolClass;

    @ManyToOne
    @MapsId("classTeacherId")
    @JoinColumn(name = "class_teacher_id")
    private Employee classTeacher;

    @OneToMany(
            mappedBy = "section",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<SectionSubject> sectionSubjects = new HashSet<>();

    public SectionId getId() {
        return id;
    }

    public Section setId(SectionId id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Section setName(String name) {
        this.name = name;
        return this;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public Section setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
        return this;
    }

    public Employee getClassTeacher() {
        return classTeacher;
    }

    public Section setClassTeacher(Employee classTeacher) {
        this.classTeacher = classTeacher;
        return this;
    }

    public Set<SectionSubject> getSectionSubjects() {
        return sectionSubjects;
    }

    public Section setSectionSubjects(Set<SectionSubject> sectionSubjects) {
        this.sectionSubjects = sectionSubjects;
        return this;
    }
}
