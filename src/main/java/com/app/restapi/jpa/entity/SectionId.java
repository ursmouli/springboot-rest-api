package com.app.restapi.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SectionId implements Serializable {

    @Column(name = "school_class_id")
    private Long schoolClassId; // Must match the name in the Entity

    @Column(name = "class_teacher_id")
    private Long classTeacherId; // Must match the name in the Entity

    public SectionId() {}

    public SectionId(Long schoolClassId, Long classTeacherId) {
        this.schoolClassId = schoolClassId;
        this.classTeacherId = classTeacherId;
    }

    public Long getSchoolClassId() {
        return schoolClassId;
    }

    public void setSchoolClassId(Long schoolClassId) {
        this.schoolClassId = schoolClassId;
    }

    public Long getClassTeacherId() {
        return classTeacherId;
    }

    public void setClassTeacherId(Long classTeacherId) {
        this.classTeacherId = classTeacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (getClass() != o.getClass()) return false;
        SectionId that = (SectionId) o;
        return Objects.equals(schoolClassId, that.schoolClassId)
                && Objects.equals(classTeacherId, that.classTeacherId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolClassId, classTeacherId);
    }
}
