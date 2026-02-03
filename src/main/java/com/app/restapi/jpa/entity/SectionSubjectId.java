package com.app.restapi.jpa.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SectionSubjectId implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = "schoolClassId", column = @Column(name = "school_class_id")),
            @AttributeOverride(name = "classTeacherId", column = @Column(name = "class_teacher_id"))
    })
    private SectionId sectionId;

    @Column(name = "subject_id")
    private Long subjectId;

    public SectionSubjectId(){}

    public SectionSubjectId(SectionId sectionId, Long subjectId) {
        this.sectionId = sectionId;
        this.subjectId = subjectId;
    }

    public SectionId getSectionId() {
        return sectionId;
    }

    public void setSectionId(SectionId sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return true;
        if (this == o) return true;
        if (this.getClass() != o.getClass()) return false;
        SectionSubjectId that = (SectionSubjectId) o;
        return Objects.equals(sectionId, that.sectionId)
                && Objects.equals(subjectId, that.subjectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionId, subjectId);
    }
}
