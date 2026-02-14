package com.app.restapi.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "section_subject")
public class SectionSubject {

    @EmbeddedId
    private SectionSubjectId id;

    @ManyToOne
    @MapsId("sectionId")
    @JoinColumns({
            @JoinColumn(name = "school_class_id", referencedColumnName = "school_class_id"),
            @JoinColumn(name = "class_teacher_id", referencedColumnName = "class_teacher_id")
    })
    private Section section;

    @ManyToOne
    @MapsId("subjectId")
    @JoinColumn(name = "subject_id")
    private Subject subject;

    // future fields: periodsPerWeek, roomNo, etc.


    public SectionSubjectId getId() {
        return id;
    }

    public SectionSubject setId(SectionSubjectId id) {
        this.id = id;
        return this;
    }

    public Section getSection() {
        return section;
    }

    public SectionSubject setSection(Section section) {
        this.section = section;
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public SectionSubject setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    @Override
    public String toString() {
        return "SectionSubject{" +
                "id=" + id +
                ", section=" + section +
                ", subject=" + subject +
                '}';
    }
}
