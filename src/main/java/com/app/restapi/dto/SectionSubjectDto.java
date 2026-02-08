package com.app.restapi.dto;

import com.app.restapi.jpa.entity.SectionSubjectId;

public class SectionSubjectDto {

    private SectionSubjectId id;
    private SectionDto section;
    private SubjectDto subject;

    public SectionSubjectId getId() {
        return id;
    }
    public SectionSubjectDto setId(SectionSubjectId id) {
        this.id = id;
        return this;
    }

    public SectionDto getSection() {
        return section;
    }

    public SectionSubjectDto setSection(SectionDto section) {
        this.section = section;
        return this;
    }

    public SubjectDto getSubject() {
        return subject;
    }

    public SectionSubjectDto setSubject(SubjectDto subject) {
        this.subject = subject;
        return this;
    }
}
