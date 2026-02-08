package com.app.restapi.converter;

import com.app.restapi.dto.SectionDto;
import com.app.restapi.jpa.entity.Section;
import com.app.restapi.jpa.entity.SectionId;
import org.springframework.stereotype.Component;

@Component
public class SectionConverter implements GenericConverter<Section, SectionDto> {

    private final EmployeeConverter employeeConverter;
    private final SchoolClassConverter schoolClassConverter;
    private final SectionSubjectConverter sectionSubjectConverter;

    public SectionConverter(EmployeeConverter employeeConverter,
                            SchoolClassConverter schoolClassConverter,
                            SectionSubjectConverter sectionSubjectConverter) {
        this.employeeConverter = employeeConverter;
        this.schoolClassConverter = schoolClassConverter;
        this.sectionSubjectConverter = sectionSubjectConverter;
    }

    @Override
    public Section toEntity(SectionDto dto) {
        return new Section()
                .setName(dto.getName())
                .setClassTeacher(employeeConverter.toEntity(dto.getClassTeacher()))
                .setSchoolClass(schoolClassConverter.toEntity(dto.getSchoolClass()));
    }

    @Override
    public SectionDto toDto(Section entity) {
        SectionDto dto = new SectionDto()
                .setId(new SectionId(entity.getSchoolClass().getId(), entity.getClassTeacher().getId()))
                .setName(entity.getName())
                .setClassTeacher(employeeConverter.toDto(entity.getClassTeacher()))
                .setSchoolClass(schoolClassConverter.toDto(entity.getSchoolClass()));

        if (entity.getSectionSubjects() != null) {
            entity.getSectionSubjects().forEach(sectionSubject -> dto.getSectionSubjects().add(sectionSubjectConverter.toDto(sectionSubject)));
        }

        return dto;
    }
}
